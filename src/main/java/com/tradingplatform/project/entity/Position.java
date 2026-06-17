package com.tradingplatform.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "positions") // Prevents SQL keyword conflicts
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String symbol;
    private int quantity;
    private double entryPrice;
    private Double stopLoss;
    private Double takeProfit;
    
    private boolean active = true;

    // Default Constructor (Required by JPA)
    public Position() {
    }

    // Parameterized Constructor (For easy instantiation in services)
    public Position(Long userId, String symbol, int quantity, double entryPrice, Double stopLoss, Double takeProfit) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.active = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(Double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public Double getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(Double takeProfit) {
        this.takeProfit = takeProfit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}