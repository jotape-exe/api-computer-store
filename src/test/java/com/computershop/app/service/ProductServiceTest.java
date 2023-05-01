package com.computershop.app.service;

import com.computershop.app.AppApplicationTests;
import com.computershop.app.model.Product;
import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.impl.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DisplayName("ProductServiceTest")
public class ProductServiceTest extends AppApplicationTests {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    private Product product;

    private List<Product> productList;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setAmount(12);
        product.setName("Test Product");
        product.setDescription("Description test");
        product.setManufacturer("Test Manufacturer");
        product.setValue(333.30);

        productList = new ArrayList<>();
        productList.add(new Product(1L,"Generic Manufacturer","Generic Product","Generic Manufacturer",766.54,80));
        productList.add(new Product(2L,"X Manufacturer","X Product","X Manufacturer",123.76,120));
    }

    @Test
    @DisplayName("Deve retornar um produto valido")
    public void deveRetornarUmProdutoValido(){
        Mockito.when(productRepository.save(ArgumentMatchers.eq(product))).thenReturn(product);

        productService.create(product);
    }

    @Test
    @DisplayName("Deve apagar um produto")
    public void deveApagarProduto(){
        Long productId = 1L;

        Mockito.when(productRepository.findById(ArgumentMatchers.eq(productId))).thenReturn(Optional.of(product));
        productService.delete(productId);

        Mockito.verify(productRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Product.class));
    }

    @Test
    @DisplayName("Deve listar diversos produtos")
    public void deveListarProdutos(){
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        productService.findAll();
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia de produtos")
    public void deveRetornarUmaListaVazia(){
        Mockito.when(productRepository.findAll()).thenReturn(null); // Collection.emptyList()
        productService.findAll();
    }
}
