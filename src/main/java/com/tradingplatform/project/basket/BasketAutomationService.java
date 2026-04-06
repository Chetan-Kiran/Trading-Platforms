package com.tradingplatform.project.basket;

import com.tradingplatform.project.entity.Asset;
import com.tradingplatform.project.entity.Basket;
import com.tradingplatform.project.entity.BasketAsset;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;
import com.tradingplatform.project.repository.BasketRepository;
import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.trade.TradeService;

public class BasketAutomationService {
    private final BasketRepository basketRepository;
    private final MarketPriceService marketPriceService;
    private final TradeService tradeService;

    public BasketAutomationService(BasketRepository basketRepository,
                                   MarketPriceService marketPriceService,
                                   TradeService tradeService) {
        this.basketRepository = basketRepository;
        this.marketPriceService = marketPriceService;
        this.tradeService = tradeService;
    }
    public void executeBasket(Long basketId){

    Basket basket =
        basketRepository
            .findById(basketId)
            .orElseThrow();

    for(BasketAsset ba
            : basket.getAssets()){

        Asset asset =
            ba.getAsset();

        double price =
            marketPriceService
                .getPrice(
                    asset.getSymbol()
                );

        boolean matched =
            ba.getConditionType()
                .equals("LESS_THAN")
            &&
            price < ba.getThreshold();

        if(matched){

            TradeRequestDTO dto =
                new TradeRequestDTO();

            dto.setUserId(
                basket.getUser()
                      .getId()
            );

            dto.setAssetId(
                asset.getId()
            );

            dto.setQuantity(
                ba.getQuantity()
            );

            tradeService.buy(dto);

            System.out.println(
                "BOUGHT "
                + asset.getSymbol()
            );
        }
    }
}
}
