package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class EMAStrategy
implements TradingStrategy {

    @Override
    public boolean evaluate(
        double price,
        double ema
    ){

        return price > ema;
    }
}
