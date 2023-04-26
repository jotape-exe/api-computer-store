package com.computershop.app.service;

import com.computershop.app.AppApplicationTests;
import com.computershop.app.model.Product;
import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.impl.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("ProductServiceTest")
public class ProductServiceTest extends AppApplicationTests {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("Deve retornar um produto valido")
    public void deveRetornarUmProdutoValido(){
        Product product = createProduct();
        Mockito.when(productRepository.save(ArgumentMatchers.eq(product))).thenReturn(product);

        productService.create(product);
    }

    private Product createProduct(){
        Product product = Mockito.mock(Product.class);

        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(product.getAmount()).thenReturn(12);
        Mockito.when(product.getName()).thenReturn("Test Product");
        Mockito.when(product.getDescription()).thenReturn("Description test");
        Mockito.when(product.getManufacturer()).thenReturn("Test Manufacturer");
        Mockito.when(product.getValue()).thenReturn(333.30);

        return product;
    }
}
