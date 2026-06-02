package com.tradingplatform.project.automation.dto;

public class AutomationRuleDTO {

    private Long userId;
    private String symbol;
    private String conditionType; // e.g., "LESS_THAN", "GREATER_THAN"
    private double threshold;     // Target price trigger
    private String actionType;    // e.g., "BUY", "SELL"
    private int quantity;

    // Default Constructor
    public AutomationRuleDTO() {
    }

    // Parameterized Constructor
    public AutomationRuleDTO(Long userId, String symbol, String conditionType, double threshold, String actionType, int quantity) {
        this.userId = userId;
        this.symbol = symbol;
        this.conditionType = conditionType;
        this.threshold = threshold;
        this.actionType = actionType;
        this.quantity = quantity;
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
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}