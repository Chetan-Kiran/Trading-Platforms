package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    
}
