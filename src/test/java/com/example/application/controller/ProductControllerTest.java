package com.example.application.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.domain.model.Product;
import com.example.domain.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Mockito.reset(productService);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(100.0);

        Mockito.when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Product1\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product2");
        product.setPrice(200.0);

        Mockito.when(productService.getProductById(anyLong())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product2"))
                .andExpect(jsonPath("$.price").value(200.0));
    }

    @Test
    void shouldReturnNotFoundIfProductNotExists() throws Exception {
        Mockito.when(productService.getProductById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/products/999"))
                .andExpect(status().isNotFound());
    }
}
