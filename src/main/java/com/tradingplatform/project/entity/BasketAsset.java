package com.tradingplatform.project.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="basket_assets")
public class BasketAsset {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="basket_id")
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name="asset_id")
    private Asset asset;

    @Column(name = "condition_type")
    private String conditionType;

    private double threshold;

    private int quantity;

    // --- Constructors ---
    
    public BasketAsset() {
    }

    public BasketAsset(Basket basket, Asset asset, String conditionType, double threshold, int quantity) {
        this.basket = basket;
        this.asset = asset;
        this.conditionType = conditionType;
        this.threshold = threshold;
        this.quantity = quantity;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
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