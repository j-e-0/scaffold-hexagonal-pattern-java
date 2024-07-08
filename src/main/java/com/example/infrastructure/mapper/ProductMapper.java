package com.example.infrastructure.mapper;

import com.example.domain.model.Product;
import com.example.infrastructure.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }

    public static Product toModel(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        return product;
    }
}
