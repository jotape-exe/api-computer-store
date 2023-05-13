package com.computershop.app.controller;

import com.computershop.app.model.Product;
import com.computershop.app.model.dto.request.ProductDTO;
import com.computershop.app.model.dto.response.ProductView;
import com.computershop.app.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductView> getProductById(@PathVariable Long id){
        Product product = this.productService.findById(id);
        return ResponseEntity.ok().body(new ProductView(product));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductView>> getAllProducts(){
        List<ProductView> products = this.productService.findAll()
                .stream()
                .map(ProductView::new)
                .toList();
        return ResponseEntity.of(Optional.of(products));

    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductDTO productDTO){
        Product product = this.productService.create(productDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long id){
        Product product = this.productService.findById(id);
        Product toUpdate = productDTO.toEntity(product);
        this.productService.update(toUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
