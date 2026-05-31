package com.tradingplatform.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradingplatform.project.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
