package com.tradingplatform.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.BasketAsset;

public interface BasketAssetRepository extends JpaRepository<BasketAsset,Long>{
    Optional<BasketAsset>
    findByBasketIdAndAssetId(
        Long basketId,
        Long assetId
    );
}
