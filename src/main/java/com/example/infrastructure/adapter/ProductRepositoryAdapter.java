package com.example.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.Product;
import com.example.domain.port.ProductRepositoryPort;
import com.example.infrastructure.entity.ProductEntity;
import com.example.infrastructure.mapper.ProductMapper;
import com.example.infrastructure.repository.ProductJpaRepository;

@Service
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    public void setProductJpaRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity savedEntity = productJpaRepository.save(entity);
        return ProductMapper.toModel(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> entity = productJpaRepository.findById(id);
        return entity.map(ProductMapper::toModel);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream().map(ProductMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
}
