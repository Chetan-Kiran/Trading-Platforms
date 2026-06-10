package com.tradingplatform.project.indicators;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EMAIndicator {

    public double calculate(
        List<Double> prices,
        int period
    ){

        if(prices.size() < period){

            throw new RuntimeException(
                "Not enough data for EMA"
            );
        }

        double multiplier =
            2.0 / (period + 1);

        double ema = prices.get(0);

        for(int i=1;i<prices.size();i++){

            ema =
                ((prices.get(i) - ema)
                * multiplier)
                + ema;
        }

        return ema;
    }
}