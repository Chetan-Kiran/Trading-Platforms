package com.tradingplatform.project.basket;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.entity.User;
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

    public BasketService(
    BasketRepository basketRepository,
    UserRepository userRepository,
    AssetRepository assetRepository,
    BasketAssetRepository
    basketAssetRepository){

    this.basketRepository = basketRepository;

    this.userRepository = userRepository;

    this.assetRepository = assetRepository;

    this.basketAssetRepository = basketAssetRepository;
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

    public void addAsset(Long basketId,Long assetId) {

    Basket basket =
        basketRepository
        .findById(basketId)
        .orElseThrow(
            () ->new RuntimeException("Basket not found")
        );

    Asset asset =
        assetRepository
        .findById(assetId)
        .orElseThrow();

    BasketAsset relation =
        new BasketAsset();

    relation.setBasket(
        basket
    );

    relation.setAsset(
        asset
    );

    basketAssetRepository
        .save(relation);

}

public Basket getBasket(Long basketId) {

    return basketRepository
        .findById(basketId)
        .orElseThrow();

}
}
