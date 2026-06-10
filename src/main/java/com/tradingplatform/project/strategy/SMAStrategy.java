package com.tradingplatform.project.strategy;



import org.springframework.stereotype.Component;

@Component
public class SMAStrategy
implements TradingStrategy {

    @Override
    public boolean evaluate(
        double price,
        double sma
    ){
        return price > sma;
    }
}