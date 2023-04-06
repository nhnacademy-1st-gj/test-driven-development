package com.nhnacademy.gw4.sender;

import com.nhnacademy.gw4.Customer;

public interface MessageSender {
    boolean sendMessage(Customer customer, String message);
}
