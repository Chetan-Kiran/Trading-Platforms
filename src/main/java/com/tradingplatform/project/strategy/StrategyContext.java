package com.tradingplatform.project.strategy;

import org.springframework.stereotype.Component;

@Component
public class StrategyContext {

    private final PriceDropStrategy priceDropStrategy;
    private final BreakoutStrategy breakoutStrategy;
    private final SMAStrategy smaStrategy;
    private final RSIStrategy rsiStrategy;
    private final EMAStrategy emaStrategy;

    public StrategyContext(
        PriceDropStrategy priceDropStrategy,
        BreakoutStrategy breakoutStrategy,
        SMAStrategy smaStrategy,
        RSIStrategy rsiStrategy,
        EMAStrategy emaStrategy
    ) {
        this.priceDropStrategy = priceDropStrategy;
        this.breakoutStrategy = breakoutStrategy;
        this.smaStrategy = smaStrategy;
        this.rsiStrategy = rsiStrategy;
        this.emaStrategy = emaStrategy;
    }

    public boolean evaluate(String strategy, double price, double threshold) {
        switch (strategy) {
            case "PRICE_DROP":
                return priceDropStrategy.evaluate(price, threshold);

            case "BREAKOUT":
                return breakoutStrategy.evaluate(price, threshold);

            case "SMA":
                return smaStrategy.evaluate(price, threshold);

            case "RSI":
                // Changed 'value' to 'price' to fix the compilation error
                return rsiStrategy.evaluate(price, threshold);
            
            case "EMA":
                return emaStrategy.evaluate(price, threshold);
            default:
                throw new RuntimeException("Unknown strategy: " + strategy);
        }
    }
}