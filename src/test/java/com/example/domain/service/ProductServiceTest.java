package com.example.domain.service;

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
import com.example.domain.port.ProductRepositoryPort;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepositoryPort productRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepositoryPort.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldCreateProduct() {
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(100.0);

        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Product1", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPrice());
    }

    @Test
    void shouldGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product2");
        product.setPrice(200.0);

        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Product2", foundProduct.get().getName());
    }

    @Test
    void shouldReturnEmptyIfProductNotExists() {
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.getProductById(999L);

        assertFalse(foundProduct.isPresent());
    }
}
