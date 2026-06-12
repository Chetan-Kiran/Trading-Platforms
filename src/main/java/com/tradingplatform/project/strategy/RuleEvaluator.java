package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class RuleEvaluator {

    public boolean evaluate(double rsi, double rsiThreshold, double price,double ema){

        boolean rsiMatch = rsi < rsiThreshold;

        boolean emaMatch = price > ema;

        return rsiMatch && emaMatch;
    }
}