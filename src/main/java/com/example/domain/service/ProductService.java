package com.example.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.Product;
import com.example.domain.port.ProductRepositoryPort;

@Service
public class ProductService {

    private ProductRepositoryPort productRepository;

    @Autowired
    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductRepository(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
