package com.nhnacademy.gw4;

public interface CustomerRepository {
    Customer findById(Long customerId);

    void insert(Customer customer);

}
