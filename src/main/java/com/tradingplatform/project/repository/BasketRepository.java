package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.Basket;

import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    @Query("""
SELECT b
FROM Basket b
LEFT JOIN FETCH b.assets
WHERE b.id = :id
""")
Optional<Basket> findByIdWithAssets(Long id);
}
