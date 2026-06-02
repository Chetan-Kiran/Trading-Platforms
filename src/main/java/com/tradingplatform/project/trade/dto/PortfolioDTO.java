package com.tradingplatform.project.trade.dto;

public class PortfolioDTO {

    private String symbol;
    private int quantity;
    private double avgPrice;
    private double currentPrice;
    private double pnl;
    private double realizedPnl;
    private double unrealizedPnl;

    public PortfolioDTO(
            String symbol,
            int quantity,
            double avgPrice,
            double currentPrice,
            double pnl,
            double realizedPnl,
            double unrealizedPnl
    ){

        this.symbol=symbol;
        this.quantity=quantity;
        this.avgPrice=avgPrice;
        this.currentPrice=currentPrice;
        this.pnl=pnl;
        this.realizedPnl = realizedPnl;
        this.unrealizedPnl = unrealizedPnl;
    }

    public double getrealizedPnl(){
        return realizedPnl;
    }

    public double getunrealizedPnl(){
        return unrealizedPnl;
    }
    
    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPnl() {
        return pnl;
    }
}