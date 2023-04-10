package com.computershop.app.controller;

import com.computershop.app.model.Product;
import com.computershop.app.model.dto.ProductDTO;
import com.computershop.app.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = this.productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = this.productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductDTO productDTO){
        Product product = this.productService.fromDTO(productDTO);
        this.productService.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long id){
        productDTO.setId(id);
        Product product = this.productService.fromDTO(productDTO);
        product = this.productService.update(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
