package com.tradingplatform.project.backtesting.DTO;

public class BacktestResult {

    private double startingCapital;

    private double endingCapital;

    private double profit;

    private int trades;

    public BacktestResult(
        double startingCapital,
        double endingCapital,
        double profit,
        int trades
    ){

        this.startingCapital =
            startingCapital;

        this.endingCapital =
            endingCapital;

        this.profit =
            profit;

        this.trades =
            trades;
    }

    public double getStartingCapital() {
        return startingCapital;
    }

    public double getEndingCapital() {
        return endingCapital;
    }

    public double getProfit() {
        return profit;
    }

    public int getTrades() {
        return trades;
    }
}