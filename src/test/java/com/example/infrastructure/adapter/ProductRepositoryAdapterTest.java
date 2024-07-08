package com.example.infrastructure.adapter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;

import com.example.domain.model.Product;
import com.example.infrastructure.entity.ProductEntity;
import com.example.infrastructure.repository.ProductJpaRepository;

public class ProductRepositoryAdapterTest {

    private ProductRepositoryAdapter productRepositoryAdapter;
    private ProductJpaRepository productJpaRepository;

    @BeforeEach
    void setUp() {
        productJpaRepository = Mockito.mock(ProductJpaRepository.class);
        productRepositoryAdapter = new ProductRepositoryAdapter(productJpaRepository);
    }

    @Test
    void shouldSaveProduct() {
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Product1");
        entity.setPrice(100.0);

        Mockito.when(productJpaRepository.save(any(ProductEntity.class))).thenReturn(entity);

        Product product = new Product();
        product.setName("Product1");
        product.setPrice(100.0);

        Product savedProduct = productRepositoryAdapter.save(product);

        assertNotNull(savedProduct);
        assertEquals("Product1", savedProduct.getName());
        assertEquals(100.0, savedProduct.getPrice());
    }

    @Test
    void shouldFindProductById() {
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Product2");
        entity.setPrice(200.0);

        Mockito.when(productJpaRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        Optional<Product> foundProduct = productRepositoryAdapter.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Product2", foundProduct.get().getName());
    }

    @Test
    void shouldReturnEmptyIfProductNotExists() {
        Mockito.when(productJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productRepositoryAdapter.findById(999L);

        assertFalse(foundProduct.isPresent());
    }
}
