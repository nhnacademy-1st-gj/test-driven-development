package com.nhnacademy.gw4;

import com.nhnacademy.gw4.domain.CustomerReceivedDetail;
import com.nhnacademy.gw4.sender.MessageSender;

public class Customer {
    private final Long id;

    public CustomerReceivedDetail getReceivedDetail() {
        return receivedDetail;
    }

    public MessageSender getWantToReceive() {
        return wantToReceive;
    }

    private CustomerReceivedDetail receivedDetail;

    public void setReceivedDetail(CustomerReceivedDetail receivedDetail) {
        this.receivedDetail = receivedDetail;
    }

    public void setWantToReceive(MessageSender wantToReceive) {
        this.wantToReceive = wantToReceive;
    }

    private MessageSender wantToReceive;

    private Long money; //원시 타입으로 하면 됨. wrapper 타입이면 Null 체크를 해야함
    private Long point;
    private String message;
    public Customer(Long id) {
        this.id = id;
        this.point=0L;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
    public Long getPoint(){
        return this.point;
    }
    public void setPoint(Long point){
        this.point=point;
    }

    public Long getCustomerId() {
        return this.id;
    }
}
