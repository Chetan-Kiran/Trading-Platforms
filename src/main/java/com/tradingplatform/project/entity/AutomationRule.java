package com.tradingplatform.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "automation_rules")
public class AutomationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String symbol;

    private String conditionType; // e.g., "LESS_THAN", "GREATER_THAN"

    private double threshold;     // Target price trigger

    @Column(name = "action")
    private String action;    // e.g., "BUY", "SELL"

    private int quantity;

    private boolean active = true;

    private String strategy;

    @Column(name = "stop_loss")
     Double stopLoss;

    @Column(name = "take_profit")
    private Double takeProfit;

    // Default Constructor (Required by JPA)
    public AutomationRule() {
    }

    // Parameterized Constructor
    public AutomationRule(Long userId, String symbol, String conditionType, double threshold, String action, int quantity) {
        this.userId = userId;
        this.symbol = symbol;
        this.conditionType = conditionType;
        this.threshold = threshold;
        this.action = action;
        this.quantity = quantity;
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

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getActionType() {
        return action;
    }

    public void setActionType(String action) {
        this.action = action;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStrategy() {
    return strategy;
}

public void setStrategy(String strategy){
    this.strategy = strategy;
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

public String getAction() {
    return action;
}

public void setAction(String action) {
    this.action = action;
}
}