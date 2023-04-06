package com.nhnacademy.gw4;

import com.nhnacademy.gw4.sender.MessageSender;

public class FakeSmsMessageSender implements MessageSender {
    @Override
    public boolean sendMessage(Customer customer, String message) {
        if(message == null){
            return false;
        }
        return true;
    }
}
