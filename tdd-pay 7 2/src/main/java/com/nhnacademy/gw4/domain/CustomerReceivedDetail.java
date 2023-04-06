package com.nhnacademy.gw4.domain;

public class CustomerReceivedDetail {
    private String Email;
    private String Sms;
    private String dooray;

    public CustomerReceivedDetail() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSms() {
        return Sms;
    }

    public void setSms(String sms) {
        Sms = sms;
    }

    public String getDooray() {
        return dooray;
    }

    public void setDooray(String dooray) {
        this.dooray = dooray;
    }
}
