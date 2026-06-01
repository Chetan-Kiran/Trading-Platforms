package com.tradingplatform.project.market.dto;

import java.util.List;

public class BasketValuationDTO {

    private Long basketId;

    private String basketName;

    private List<String> holdings;

    private Double totalValue;

    public BasketValuationDTO(
            Long basketId,
            String basketName,
            List<String> holdings,
            Double totalValue
    ){

        this.basketId=basketId;
        this.basketName=basketName;
        this.holdings=holdings;
        this.totalValue=totalValue;
    }

    public Long getBasketId(){
        return basketId;
    }

    public String getBasketName(){
        return basketName;
    }

    public List<String> getHoldings(){
        return holdings;
    }

    public Double getTotalValue(){
        return totalValue;
    }
}
