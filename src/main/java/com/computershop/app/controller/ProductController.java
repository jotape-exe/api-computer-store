package com.computershop.app.controller;

import com.computershop.app.model.Product;
import com.computershop.app.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
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
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        this.productService.create(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
        product = this.productService.update(product);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws RuntimeException{
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
