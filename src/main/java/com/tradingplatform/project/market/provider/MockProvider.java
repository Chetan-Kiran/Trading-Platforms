package com.tradingplatform.project.market.provider;

public class MockProvider implements MarketProvider {
    public double getPrice(
    String symbol
){

    if(symbol.equals("AAPL"))
        return 295;

    if(symbol.equals("MSFT"))
        return 430;

    if(symbol.equals("GOOGL"))
        return 170;

    return 100;
}
}
