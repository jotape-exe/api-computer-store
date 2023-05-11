package com.computershop.app.controller;

import com.computershop.app.AppApplicationTests;
import com.computershop.app.model.Product;
import com.computershop.app.model.dto.request.ProductDTO;
import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.impl.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("ProductControllerTest")
public class ProductControllerTest extends AppApplicationTests {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    public static final String URL = "/products/create";
    private ProductDTO productDTO;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();

        productDTO = new ProductDTO();
        productDTO.setAmount(12);
        productDTO.setName("Test Product");
        productDTO.setDescription("Description test");
        productDTO.setManufacturer("Test Manufacturer");
        productDTO.setValue(333.30);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve realizar o post de um product")
    void createProductAndReturn201Status() throws Exception {
        String asString = objectMapper.writeValueAsString(productDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asString)
        ).andExpect(status().isCreated());

        verify(productService, times(1)).create(any(Product.class));
    }
}
