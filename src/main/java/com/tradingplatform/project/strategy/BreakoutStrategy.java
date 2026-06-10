package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class BreakoutStrategy implements TradingStrategy {

    @Override
    public boolean evaluate(double price,double threshold){
        return price > threshold;
    }
}