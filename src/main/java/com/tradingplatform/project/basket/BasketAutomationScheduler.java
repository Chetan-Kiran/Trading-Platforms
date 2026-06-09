package com.tradingplatform.project.basket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.tradingplatform.project.repository.BasketRepository;

@Component
public class BasketAutomationScheduler {

    private final BasketRepository basketRepository;
    private final BasketAutomationService basketAutomationService;

    public BasketAutomationScheduler(
            BasketRepository basketRepository,
            BasketAutomationService basketAutomationService
    ) {
        this.basketRepository = basketRepository;
        this.basketAutomationService = basketAutomationService;
    }

    @Scheduled(fixedRate = 60000)
    public void run() {
        basketRepository.findAll()
            .forEach(
                basket ->
                    basketAutomationService
                        .executeBasket(
                            basket.getId()
                        )
            );
    }
}
