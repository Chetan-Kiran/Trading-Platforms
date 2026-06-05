package com.tradingplatform.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name="wallets")
public class Wallet {

    @Id
    private Long userId;

    private double balance;

    public Wallet(){}

    public Wallet(
        Long userId,
        double balance
    ){
        this.userId=userId;
        this.balance=balance;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId=userId;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance=balance;
    }
}
