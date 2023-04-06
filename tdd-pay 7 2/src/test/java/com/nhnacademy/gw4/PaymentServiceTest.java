package com.nhnacademy.gw4;

import com.nhnacademy.gw4.coupon.Coupon;
import com.nhnacademy.gw4.coupon.FixedAmountCoupon;
import com.nhnacademy.gw4.coupon.PercentCoupon;
import com.nhnacademy.gw4.domain.CustomerReceivedDetail;
import com.nhnacademy.gw4.exception.*;
import com.nhnacademy.gw4.sender.MessageSenderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    // SUT
    PaymentService service;
    // DOC
    CustomerRepository repository;
    Customer testCustomer;


    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);

        service = new PaymentService(repository);

        testCustomer = new Customer(123123L);
        testCustomer.setMoney(100000L);
        testCustomer.setPoint(10000L);
        testCustomer.setWantToReceive(MessageSenderType.SMS);
        CustomerReceivedDetail detail = new CustomerReceivedDetail();
        detail.setDooray("dooray");
        detail.setEmail("dooray");
        detail.setSms("dooray");
        testCustomer.setReceivedDetail(detail);
    }

    @Test
    void pay_notFoundCustomer_thenThrowCustomerNotFoundException() {
        long amount = 10_000L;
        Long customerId = 3423432L;

        when(repository.findById(customerId)).thenReturn(null);

        assertThatThrownBy(() -> service.pay(amount, customerId))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Not found customer", customerId.toString());
    }

    /**
     * 가격이 음수면 InvalidAmountException 발생.
     */
    @Test
    void pay_invalidAmount_thenThrowInvalidAmountException() {
        // TODO: Keep going~
        long amount = -123L;
        Long customerId = 123135L;

        assertThatThrownBy(()-> service.pay(amount, customerId)).isInstanceOf(InvalidAmountException.class)
                .hasMessageContaining("Invalid Amount", amount);
    }

    /**
     * pay 성공시 Receipt 타입 객체 반환.
     */
    @Test
    void pay_thenReturnReceipt_whenSuccess(){
        long amount = 10_000L;
        Long customerId = 1212121L;

        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThat(service.pay(amount, customerId)).isInstanceOf(Receipt.class);
    }

    /**
     * 계산 성공시 포인트 검증.
     */
    @Test
    void pay_calculatePoint_whenPaySuccess(){
        long amount = 1000L;
        Long customerId = 123123L;

        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThat(service.pay(amount,customerId).getPoint()).isEqualTo(amount / 100);
    }

    /**
     * 계산 성공 시 고객의 포인트에 추가 됐는지 검증.
     */
    @Test
    void pay_pointSave_toCustomer(){
        long amount = 1000L;
        Long customerId = 123123L;

        when(repository.findById(customerId)).thenReturn(testCustomer);

        Long beforePoint = testCustomer.getPoint();

        Long payPoint = service.pay(amount,customerId).getPoint();

        assertThat(testCustomer.getPoint()).isEqualTo(beforePoint + payPoint);
    }

    /**
     * 고객 돈이 모자랄 때 NotEnoughMoneyException 발생.
     */
    @Test
    void pay_customer_notEnoughMoney() {
        long amount = 1000L;
        Long customerId = 123123L;

        when(repository.findById(customerId)).thenReturn(testCustomer);
        testCustomer.setMoney(100L);

        assertThatThrownBy(()-> service.pay(amount, customerId)).isInstanceOf(NotEnoughMoneyException.class)
                .hasMessageContaining("Not enough money", amount);
        ve
    }

    /**
     * 포인트결제 시 포인트가 모자랄때  NotEnoughPointException 발생.
     */
    @Test
    void pay_customer_notEnoughPoint() {
        long amount = 1000L;
        Long customerId = 123123L;

        when(repository.findById(customerId)).thenReturn(testCustomer);
        testCustomer.setPoint(10L);

        assertThatThrownBy(() -> service.pay(amount, customerId,100L)).isInstanceOf(NotEnoughPointException.class)
                .hasMessageContaining("Not Enough Point", testCustomer.getPoint());
    }


    /**
     * 포인트 결제 성공 시, 포인트 차감 성공 검증.
     */
    @Test
    void pay_withPoint_success(){
        long amount = 1000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);

        Long usePoint = 100L;

        assertThat(service.pay(amount,customerId,usePoint).getAmount()).isEqualTo(amount-usePoint);
    }

    /**
     * amount 보다 point 가 많을 경우
     */
    @Test
    void pay_pointIsMoreThanAmount(){
        long amount = 1000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);
        testCustomer.setPoint(2000L);
        assertThat(service.pay(amount,customerId, testCustomer.getPoint()).getAmount()).isEqualTo(0L);
        assertThat(testCustomer.getPoint()).isEqualTo(1000L);

    }

    /**
     * 결제 성공 시, 메세지 전송.
     */
    @Test
    void pay_sendMessage_whenSuccess(){
        long amount = 1000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);
        assertThat(service.pay(amount,customerId,100L).getAmount());
    }

    /**
     * Fake 메세지 전송 성공 시 검증
     */
    @Test
    void fakeSmsMessageSenderSuccessTest(){
        FakeSmsMessageSender fakeSmsMessageSender = new FakeSmsMessageSender();

        testCustomer.setWantToReceive(fakeSmsMessageSender);
        assertThat(service.sendMessage(testCustomer)).isTrue();

    }

    /**
     * Fake 메세지 전송 실패 시 결제 성공 검증
     */
    @Test
    void dummySmsMessageSenderFailedTest(){
        long amount = 1000L;
        Long customerId = 123123L;
        FakeSmsMessageSender fakeSmsMessageSender = new FakeSmsMessageSender();

        testCustomer.getReceivedDetail().setSms(null);
        testCustomer.setWantToReceive(fakeSmsMessageSender);
        when(repository.findById(customerId)).thenReturn(testCustomer);
        assertThat(service.pay(amount, customerId)).isInstanceOf(Receipt.class);

        //전송 실패하면 메세지 전송하는 로직이 never 호출되지 않았는지 확인하면 된다

        //when thenThrow(new IOException) 이런식으로도 결제 실패하게 만들 수 있음
    }

    /**
     * 정액쿠폰 사용 시, 총 결제금액(amount)가 쿠폰 금액만큼 차감 됐는지 확인.
     */
    @Test
    void pay_withCoupon_FixedAmount(){
        long amount = 10_000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThat(service.pay(amount, customerId, new FixedAmountCoupon(1000L)).getAmount()).isEqualTo(9000L);
    }

    /**
     * 퍼센트 쿠폰 사용 시, 총 결제금액(amount)가 쿠폰 금액만큼 차감 됐는지 확인.
     */
    @Test
    void pay_withCoupon_Percent(){
        long amount = 10_000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThat(service.pay(amount, customerId, new PercentCoupon(10L)).getAmount()).isEqualTo(9000L);
    }

    /**
     * 쿠폰이 없는데 사용 시도 시, InvalidCouponException 발생.
     */
    @Test
    void pay_withCoupon_InvalidCoupon(){
        long amount = 10_000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThatThrownBy(() -> service.pay(amount, customerId,(Coupon) null)).isInstanceOf(InvalidCouponException.class)
                .hasMessageContaining("Invalid coupon", testCustomer.getCustomerId());
    }

    /**
     * 퍼센트 쿠폰 사용 시, 100% 초과하는 쿠폰 사용 시 처리 검증
     */
    @Test
    void pay_withCoupon_PercentOver(){
        long amount = 10_000L;
        Long customerId = 123123L;
        when(repository.findById(customerId)).thenReturn(testCustomer);

        assertThat(service.pay(amount, customerId, new PercentCoupon(110L)).getAmount()).isEqualTo(0);
    }
}