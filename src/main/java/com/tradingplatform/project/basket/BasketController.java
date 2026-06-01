package com.tradingplatform.project.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.entity.Basket;
import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.market.dto.BasketResponseDTO;
import com.tradingplatform.project.market.dto.BasketValuationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public BasketResponseDTO getBasket(@PathVariable Long basketId) {

        return basketService.getBasket(basketId);

    }

    @DeleteMapping("/{basketId}/asset")
    public void removeAsset(@PathVariable Long basketId,@RequestParam Long assetId){

        basketService.removeAsset(basketId,assetId);
    }

    @GetMapping("/{basketId}/valuation")
    public BasketValuationDTO getBasketValuation(@PathVariable Long basketId){

    return basketService.getBasketValuation(basketId);
}

    @Autowired
    private MarketPriceService marketPriceService;

    @GetMapping("/price")
    public Double price(@RequestParam String symbol) {
        return marketPriceService.getPrice(symbol);
    }
    
}
