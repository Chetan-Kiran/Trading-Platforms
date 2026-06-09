package com.tradingplatform.project.basket;

import org.springframework.stereotype.Service;
import java.util.*;

import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.entity.User;
import com.tradingplatform.project.market.dto.BasketResponseDTO;
import com.tradingplatform.project.market.dto.BasketValuationDTO;
import com.tradingplatform.project.entity.Asset;
import com.tradingplatform.project.entity.Basket;
import com.tradingplatform.project.entity.BasketAsset;
import com.tradingplatform.project.repository.AssetRepository;
import com.tradingplatform.project.repository.BasketAssetRepository;
import com.tradingplatform.project.repository.BasketRepository;
import com.tradingplatform.project.repository.UserRepository;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final BasketAssetRepository basketAssetRepository;
    private final MarketPriceService marketPriceService;

    public BasketService(
    BasketRepository basketRepository,
    UserRepository userRepository,
    AssetRepository assetRepository,
    BasketAssetRepository basketAssetRepository,
    MarketPriceService marketPriceService){

    this.basketRepository = basketRepository;

    this.userRepository = userRepository;

    this.assetRepository = assetRepository;

    this.basketAssetRepository = basketAssetRepository;

    this.marketPriceService = marketPriceService;
    }

    public Basket createBasket(Long userId,String basketName){

    System.out.println("START CREATE");

    User user =
        userRepository
        .findById(userId)
        .orElseThrow(
            ()->new RuntimeException("USER NOT FOUND")
        );

    System.out.println(user.getUsername());

    Basket basket = new Basket();

    basket.setName(basketName);

    basket.setUser(user);
    
    Basket saved =
        basketRepository.save(basket);

    System.out.println(
        "SAVED ID = " + saved.getId()
    );

    return saved;
}

    public void addAsset(
        Long basketId,
        Long assetId
) {

    Basket basket =
        basketRepository
        .findById(basketId)
        .orElseThrow(
            ()->new RuntimeException(
                "Basket not found"
            )
        );

    Asset asset =
        assetRepository
        .findById(assetId)
        .orElseThrow(
            ()->new RuntimeException(
                "Asset not found"
            )
        );

    Optional<BasketAsset> existing =
        basketAssetRepository
        .findByBasketIdAndAssetId(
            basketId,
            assetId
        );

    if(existing.isPresent()){

        throw new RuntimeException(
            "Asset already exists in basket"
        );

    }

    BasketAsset relation =
        new BasketAsset();

    relation.setBasket(basket);

    relation.setAsset(asset);

    basketAssetRepository.save(
        relation
    );
}

public BasketResponseDTO getBasket(
        Long basketId
){

    Basket basket =
        basketRepository
        .findById(basketId)
        .orElseThrow();

    List<String> assetNames =
        basket.getAssets()
        .stream()
        .map(
            relation ->
                relation
                .getAsset()
                .getSymbol()
        )
        .toList();

    return new BasketResponseDTO(

        basket.getId(),

        basket.getName(),

        assetNames
    );
}

public void removeAsset(
        Long basketId,
        Long assetId
) {

    BasketAsset relation =
        basketAssetRepository
        .findByBasketIdAndAssetId(
            basketId,
            assetId
        )
        .orElseThrow(
            () -> new RuntimeException(
                "Asset not found in basket"
            )
        );

    basketAssetRepository.delete(
        relation
    );
}

public BasketValuationDTO getBasketValuation(Long basketId){

    Basket basket =
        basketRepository
            .findById(basketId)
            .orElseThrow();

    List<String> holdings =
        new ArrayList<>();

    double total = 0;

    for(BasketAsset relation : basket.getAssets()){

        Asset asset =
            relation.getAsset();

        try{

            double livePrice =
                marketPriceService
                    .getPrice(
                        asset.getSymbol()
                    );

            holdings.add(
                asset.getSymbol()
                + " : $"
                + livePrice
            );

            total += livePrice;

        }catch(Exception e){

            System.out.println(
                "Failed to fetch price for "
                + asset.getSymbol()
            );

            holdings.add(
                asset.getSymbol()
                + " : PRICE_UNAVAILABLE"
            );
        }
    }

    return new BasketValuationDTO(
        basket.getId(),
        basket.getName(),
        holdings,
        total
    );
}

    public void addAssetRule(Long basketId, BasketAssetRuleDTO dto){
        Basket basket =
            basketRepository
            .findById(basketId)
            .orElseThrow(
                () -> new RuntimeException(
                    "Basket not found"
                )
            );

        Asset asset =
            assetRepository
            .findById(dto.getAssetId())
            .orElseThrow(
                () -> new RuntimeException(
                    "Asset not found"
                )
            );

        BasketAsset relation =
            new BasketAsset();

        relation.setBasket(basket);

        relation.setAsset(asset);

        relation.setConditionType(
            dto.getConditionType()
        );

        relation.setThreshold(
            dto.getThreshold()
        );

        relation.setQuantity(
            dto.getQuantity()
        );

        basketAssetRepository.save(
            relation
        );
    }
}
