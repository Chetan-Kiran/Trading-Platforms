package com.tradingplatform.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.PriceHistory;

public interface PriceHistoryRepository
extends JpaRepository<
    PriceHistory,
    Long
>{

    List<PriceHistory>
    findTop20BySymbolOrderByTimestampDesc(
        String symbol
    );
}