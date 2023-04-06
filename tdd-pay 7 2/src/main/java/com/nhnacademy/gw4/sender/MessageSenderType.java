package com.nhnacademy.gw4.sender;

import com.nhnacademy.gw4.Customer;

public enum MessageSenderType implements MessageSender {
    SMS() {
        @Override
        public boolean sendMessage(Customer customer, String message) {
            String sms = customer.getReceivedDetail().getSms();
            if(sms == null) return false;
            return true;
        }
    },
    EMAIL() {
        @Override
        public boolean sendMessage(Customer customer, String message) {
            String email = customer.getReceivedDetail().getEmail();
            if(email == null) return false;
            return true;
        }
    },
    APP() {
        @Override
        public boolean sendMessage(Customer customer, String message) {
            String dooray = customer.getReceivedDetail().getSms();
            if(dooray == null) return false;
            return true;
        }
    },
}
