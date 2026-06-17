package com.tradingplatform.project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.tradingplatform.project.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findAll();

}
