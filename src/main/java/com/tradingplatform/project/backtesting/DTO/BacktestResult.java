package com.tradingplatform.project.backtesting.DTO;

public class BacktestResult {

    private double startingCapital;

    private double endingCapital;

    private double profit;

    private int trades;

    private int wins;

    private int losses;

    private double winRate;

    public BacktestResult(
        double startingCapital,
        double endingCapital,
        double profit,
        int trades,
        int wins,
        int losses,
        double winRate
    ){

        this.startingCapital = startingCapital;

        this.endingCapital = endingCapital;

        this.profit =profit;

        this.trades =trades;

        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
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

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinRate() {
        return winRate;
    }
}