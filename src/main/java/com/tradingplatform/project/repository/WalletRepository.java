package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.Wallet;

public interface WalletRepository
extends JpaRepository<Wallet,Long>{

}