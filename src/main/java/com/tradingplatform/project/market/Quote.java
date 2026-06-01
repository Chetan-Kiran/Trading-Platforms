package com.tradingplatform.project.market;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

    @JsonProperty("05. price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(
            Double price
    ) {
        this.price = price;
    }
}