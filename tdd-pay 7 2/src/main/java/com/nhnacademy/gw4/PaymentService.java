package com.nhnacademy.gw4;

import com.nhnacademy.gw4.coupon.Coupon;
import com.nhnacademy.gw4.exception.*;

import java.util.Objects;

public class PaymentService {
    private final CustomerRepository customerRepository;

    public PaymentService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 결제처리
     *
     * @param amount     결재 금액
     * @param customerId 고객 아이디
     * @return 영수증
     */
    public Receipt pay(long amount, Long customerId) {
        isValidAmount(amount);

        Customer customer = customerRepository.findById(customerId);
        isCustomerFounded(customerId, customer);

        isEnoughMoney(amount, customer);
        Receipt receipt = new Receipt(amount, customerRepository.findById(customerId));
        customer.setPoint(receipt.getPoint() + customer.getPoint());
        sendMessage(customer);

        return receipt;
    }

    public Receipt pay(long amount, Long customerId,Long point){
        Customer customer = customerRepository.findById(customerId);
        isEnoughPoint(point, customer);

        if(amount < point){
            customer.setPoint(point-amount);
            return this.pay(0, customerId);
        }
        return this.pay(amount-point,customerId);
    }

    public Receipt pay(long amount, Long customerId, Coupon coupon){
        if(coupon ==null){
            throw new InvalidCouponException(customerId);
        }
        long resultAmount = coupon.discount(amount);
        Customer customer = customerRepository.findById(customerId);

        return new Receipt(resultAmount, customer);
    }

    public boolean sendMessage(Customer customer){
        if(Objects.isNull(customer)) {
            return false;
        }
        return customer.getWantToReceive().sendMessage(customer, "");
    }
    private void isValidAmount(long amount) {
        if(amount < 0){
            throw new InvalidAmountException(amount);
        }
    }

    private void isEnoughMoney(long amount, Customer customer) {
        if(customer.getMoney() < amount) {
            throw new NotEnoughMoneyException(customer.getMoney(), amount);
        }
    }

    private void isCustomerFounded(Long customerId, Customer customer) {
        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }
    }

    private void isEnoughPoint(Long point, Customer customer) {
        if(customer.getPoint()< point){
            throw new NotEnoughPointException(point);
        }
    }
}
