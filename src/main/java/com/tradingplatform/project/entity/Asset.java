package com.tradingplatform.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name="assets")
public class Asset {

    @Id
    @GeneratedValue(strategy=
        GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String name;

    private String market;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(
        String symbol
    ) {
        this.symbol=symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(
        String name
    ) {
        this.name=name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(
        String market
    ) {
        this.market=market;
    }

}
