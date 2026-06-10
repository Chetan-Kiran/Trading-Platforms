package com.tradingplatform.project.automation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tradingplatform.project.Pricehistory.PriceHistoryService;
import com.tradingplatform.project.market.MarketPriceService;

@Component
public class AutomationScheduler {

    private final AutomationService automationService;

    private final PriceHistoryService priceHistoryService;

    private final MarketPriceService marketPriceService;

    public AutomationScheduler(
        AutomationService automationService,
        PriceHistoryService priceHistoryService,
        MarketPriceService marketPriceService
    ){
        this.automationService =
            automationService;

        this.priceHistoryService =
            priceHistoryService;

        this.marketPriceService =
            marketPriceService;
    }

    @Scheduled(fixedRate = 60000)
    public void runAutomation(){

        System.out.println(
            "AUTO SCHEDULER RUNNING..."
        );

        automationService.run();

        try {

            priceHistoryService.savePrice(
                "AAPL",
                marketPriceService.getPrice("AAPL")
            );

            priceHistoryService.savePrice(
                "MSFT",
                marketPriceService.getPrice("MSFT")
            );

            priceHistoryService.savePrice(
                "GOOGL",
                marketPriceService.getPrice("GOOGL")
            );

        } catch(Exception e){

            System.out.println(
                "PRICE HISTORY ERROR : "
                + e.getMessage()
            );
        }
    }
}