package com.tradingplatform.project.automation.dto;

import jakarta.persistence.Column;

public class AutomationRuleDTO {

    private Long userId;
    private String symbol;
    private String strategy;  // e.g., "PRICE_DROP", "RSI", "EMA"
    
    private double threshold; // Target strategy limit / price trigger
    private int quantity;

    @Column(name = "action")
    private String action;

    @Column(name = "stop_loss")
    private Double stopLoss;

    @Column(name = "take_profit")
    private Double takeProfit;

    // Default Constructor
    public AutomationRuleDTO() {
    }

    // Parameterized Constructor
    public AutomationRuleDTO(Long userId, String symbol, String strategy, String action, 
                             double threshold, int quantity, Double stopLoss, Double takeProfit) {
        this.userId = userId;
        this.symbol = symbol;
        this.strategy = strategy;
        this.action = action;
        this.threshold = threshold;
        this.quantity = quantity;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
    }

    // Getters and Setters
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

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}