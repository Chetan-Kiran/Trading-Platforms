package com.tradingplatform.project.indicators;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RSIIndicator {

    public double calculate(
        List<Double> prices
    ){

        if(prices.size() < 2){
            return 50;
        }

        double gain = 0;
        double loss = 0;

        for(int i=1;i<prices.size();i++){

            double diff =
                prices.get(i)
                -
                prices.get(i-1);

            if(diff > 0){
                gain += diff;
            }
            else{
                loss += Math.abs(diff);
            }
        }

        if(loss == 0){
            return 100;
        }

        double rs =
            gain / loss;

        return
            100 -
            (
                100 /
                (1 + rs)
            );
    }
}