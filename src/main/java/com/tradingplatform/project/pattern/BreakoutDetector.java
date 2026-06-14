package com.tradingplatform.project.pattern;

import java.util.*;

import org.springframework.stereotype.*;

@Component
public class BreakoutDetector
implements PatternDetector {

    @Override
    public boolean detect(
        List<Double> prices
    ){

        if(prices.size() < 20){
            return false;
        }

        double latest =
            prices.get(
                prices.size()-1
            );

        double max =
            prices.stream()
                  .limit(
                      prices.size()-1
                  )
                  .max(Double::compare)
                  .orElse(0.0);

        return latest > max;
    }
}
