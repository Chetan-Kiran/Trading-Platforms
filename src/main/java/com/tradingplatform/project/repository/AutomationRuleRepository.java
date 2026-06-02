package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.AutomationRule;

public interface AutomationRuleRepository
extends JpaRepository<
    AutomationRule,
    Long
>{}