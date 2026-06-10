package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class RSIStrategy
implements TradingStrategy {

    @Override
    public boolean evaluate(
        double rsi,
        double threshold
    ){

        return rsi < threshold;
    }
}