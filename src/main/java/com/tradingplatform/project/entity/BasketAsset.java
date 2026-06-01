package com.tradingplatform.project.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="basket_assets")
public class BasketAsset {

    @Id
    @GeneratedValue(strategy=
        GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="basket_id")

    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name="asset_id")
    private Asset asset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket=basket;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset=asset;
    }
}