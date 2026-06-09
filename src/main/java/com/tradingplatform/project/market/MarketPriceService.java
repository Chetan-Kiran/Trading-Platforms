package com.tradingplatform.project.market;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.market.provider.MarketProvider;

@Service
public class MarketPriceService {

    private final MarketProvider provider;

    public MarketPriceService(
        MarketProvider provider
    ){
        this.provider = provider;
    }

    public double getPrice(
        String symbol
    ){
        return provider.getPrice(
            symbol
        );
    }
}