package com.example.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.infrastructure.entity.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
