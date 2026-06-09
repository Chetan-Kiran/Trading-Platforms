package com.tradingplatform.project.basket;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket-automation")
public class BasketAutomationController {
    private final BasketAutomationService service;

    public BasketAutomationController(BasketAutomationService service){
        this.service = service;
    }

    @PostMapping("/{basketId}/run")
    public String run(@PathVariable Long basketId){

    service.executeBasket(
        basketId
    );

    return "Basket executed";
}
}
