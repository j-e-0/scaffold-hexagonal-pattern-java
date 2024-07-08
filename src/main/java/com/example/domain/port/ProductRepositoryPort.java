package com.example.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
