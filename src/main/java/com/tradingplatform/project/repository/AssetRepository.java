package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.tradingplatform.project.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findBySymbol(
    String symbol
);
}
