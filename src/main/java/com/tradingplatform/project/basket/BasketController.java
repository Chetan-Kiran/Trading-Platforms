package com.tradingplatform.project.basket;

import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.entity.Basket;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public Basket createBasket(@RequestParam Long userId,@RequestParam String name) {
        
        return basketService.createBasket(userId,name);
    }

    @PostMapping("/{basketId}/asset")
    public void addAsset(@PathVariable Long basketId,@RequestParam Long assetId) {

        basketService.addAsset(basketId,assetId);

    }

    @GetMapping("/{basketId}")
    public Basket getBasket(@PathVariable Long basketId) {

        return basketService.getBasket(basketId);

    }
}
