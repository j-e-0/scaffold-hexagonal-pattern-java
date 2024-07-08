package com.example.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.infrastructure.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
