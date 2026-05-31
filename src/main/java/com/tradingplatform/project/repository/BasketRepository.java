package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    
}
