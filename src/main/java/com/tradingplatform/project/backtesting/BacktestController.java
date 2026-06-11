package com.tradingplatform.project.backtesting;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplatform.project.backtesting.DTO.BacktestResult;



@RestController
@RequestMapping("/backtest")
public class BacktestController {

    private final
    BacktestService backtestService;

    public BacktestController(
        BacktestService backtestService
    ){

        this.backtestService =
            backtestService;
    }

    @GetMapping
    public BacktestResult run(){

        List<Double> prices =
            List.of(

                290.0,
                295.0,
                310.0,
                330.0,
                325.0
            );

        return backtestService.run(
            prices
        );
    }
}
