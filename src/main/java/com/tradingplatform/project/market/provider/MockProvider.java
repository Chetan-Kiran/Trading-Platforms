package com.tradingplatform.project.market.provider;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MockProvider
implements MarketProvider {

    @Override
    public double getPrice(
        String symbol
    ) {

        if(symbol.equals("AAPL"))
            return 300;

        if(symbol.equals("MSFT"))
            return 430;

        if(symbol.equals("GOOGL"))
            return 170;

        return 100;
    }
}