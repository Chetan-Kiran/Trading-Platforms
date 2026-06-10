package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class PriceDropStrategy
implements TradingStrategy {

    @Override
    public boolean evaluate(
        double price,
        double threshold
    ){
        return price < threshold;
    }
}