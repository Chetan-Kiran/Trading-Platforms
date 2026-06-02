package com.tradingplatform.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.Trade;

public interface TradeRepository
extends JpaRepository<Trade,Long>{

    List<Trade> findByUserId(Long userId);

}