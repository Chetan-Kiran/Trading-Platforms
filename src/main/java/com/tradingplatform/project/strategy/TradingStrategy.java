package com.tradingplatform.project.strategy;

public interface TradingStrategy {

    boolean evaluate(
        double price,
        double threshold
    );
}