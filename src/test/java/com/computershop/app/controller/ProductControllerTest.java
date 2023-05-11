package com.computershop.app.controller;

import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.impl.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("ProductControllerTest")
public class ProductControllerTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

}
