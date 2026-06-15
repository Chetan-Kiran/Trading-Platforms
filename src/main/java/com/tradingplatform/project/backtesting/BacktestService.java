package com.tradingplatform.project.backtesting;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.backtesting.DTO.BacktestResult;

@Service
public class BacktestService {

    public BacktestResult run(
    List<Double> prices
){

    double capital = 100000;

    int shares = 0;

    int wins = 0;
    int losses = 0;
    int trades = 0;

    double buyPrice = 0;

    for(double price : prices){

        if(
            price < 300
            &&
            shares == 0
        ){

            shares = 1;

            buyPrice = price;

            capital -= price;
        }

        else if(
            price > 320
            &&
            shares > 0
        ){

            double sellPrice = price;

            double pnl =
                sellPrice - buyPrice;

            capital += sellPrice;

            if(pnl > 0){
                wins++;
            }
            else{
                losses++;
            }

            trades++;

            shares = 0;
        }
    }

    double endingCapital =
        capital;

    double profit =
        endingCapital - 100000;

    double winRate = 0;

    if(trades > 0){

        winRate =
            ((double)wins / trades)
            * 100;
    }

    return new BacktestResult(

        100000,

        endingCapital,

        profit,

        trades,

        wins,

        losses,

        winRate
    );
}
} 
