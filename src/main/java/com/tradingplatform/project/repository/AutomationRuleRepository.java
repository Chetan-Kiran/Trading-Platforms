package com.tradingplatform.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.AutomationRule;

public interface AutomationRuleRepository
extends JpaRepository<AutomationRule,Long>{

    List<AutomationRule> findByActiveTrue();

}