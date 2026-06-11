package com.tradingplatform.project.backtesting;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.backtesting.DTO.BacktestResult;

@Service
public class BacktestService {

    public BacktestResult run(
        List<Double> prices
    ){

        double capital =
            100000;

        int shares = 0;

        int trades = 0;

        for(double price : prices){

            if(price < 300){

                shares++;

                capital -= price;

                trades++;
            }

            else if(
                price > 320
                &&
                shares > 0
            ){

                shares--;

                capital += price;

                trades++;
            }
        }

        double endingCapital =
            capital;

        double profit =
            endingCapital
            - 100000;

        return new BacktestResult(

            100000,

            endingCapital,

            profit,

            trades
        );
    }
} 
