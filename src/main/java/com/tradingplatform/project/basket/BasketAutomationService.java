package com.tradingplatform.project.basket;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.entity.Asset;
import com.tradingplatform.project.entity.Basket;
import com.tradingplatform.project.entity.BasketAsset;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;
import com.tradingplatform.project.repository.BasketAssetRepository;
import com.tradingplatform.project.repository.BasketRepository;
import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.trade.TradeService;

@Service
public class BasketAutomationService {
    private final BasketRepository basketRepository;
    private final BasketAssetRepository basketAssetRepository;
    private final MarketPriceService marketPriceService;
    private final TradeService tradeService;

    public BasketAutomationService(BasketRepository basketRepository,
                                   BasketAssetRepository basketAssetRepository,
                                   MarketPriceService marketPriceService,
                                   TradeService tradeService) {
        this.basketRepository = basketRepository;
        this.basketAssetRepository = basketAssetRepository;
        this.marketPriceService = marketPriceService;
        this.tradeService = tradeService;
    }
    public void executeBasket(Long basketId){

    Basket basket =
        basketRepository
            .findById(basketId)
            .orElseThrow();

    for(BasketAsset ba : basket.getAssets()){

    if(!ba.isActive()){
        continue;
    }

    Asset asset = ba.getAsset();

    double price =
        marketPriceService.getPrice(
            asset.getSymbol()
        );

    boolean matched = false;

    if(
        "LESS_THAN".equals(
            ba.getConditionType()
        )
    ){
        matched =
            price < ba.getThreshold();
    }

    if(
        "GREATER_THAN".equals(
            ba.getConditionType()
        )
    ){
        matched =
            price > ba.getThreshold();
    }

    if(matched){

        TradeRequestDTO dto =
            new TradeRequestDTO();

        dto.setUserId(
            basket.getUser().getId()
        );

        dto.setAssetId(
            asset.getId()
        );

        dto.setQuantity(
            ba.getQuantity()
        );

        tradeService.buy(dto);

        ba.setActive(false);

        basketAssetRepository.save(
            ba
        );

        System.out.println(
            "BOUGHT "
            + asset.getSymbol()
        );
    }
}
}
}
