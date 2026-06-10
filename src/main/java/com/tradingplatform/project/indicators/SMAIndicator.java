package com.tradingplatform.project.indicators;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SMAIndicator {

    public double calculate(
        List<Double> prices
    ){

        if(prices.isEmpty()){
            return 0;
        }

        double sum = 0;

        for(Double p : prices){
            sum += p;
        }

        return sum / prices.size();
    }
}