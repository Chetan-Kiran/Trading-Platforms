package com.tradingplatform.project.basket;

public class BasketAssetRuleDTO {

    private Long assetId;
    private String conditionType;
    private double threshold;
    private int quantity;

    // --- Constructors ---

    public BasketAssetRuleDTO() {
    }

    public BasketAssetRuleDTO(Long assetId, String conditionType, double threshold, int quantity) {
        this.assetId = assetId;
        this.conditionType = conditionType;
        this.threshold = threshold;
        this.quantity = quantity;
    }

    // --- Getters and Setters ---

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}