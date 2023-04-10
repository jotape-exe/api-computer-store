package com.computershop.app.service.impl;

import com.computershop.app.model.Product;
import com.computershop.app.model.dto.ProductDTO;
import com.computershop.app.model.dto.request.ProductRequest;
import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService implements CrudService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(()-> new RuntimeException("Product not found ID -> ("+id+")"));
    }

    @Override
    public ArrayList<Product> findAll() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        product.setId(null);
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product newProduct = findById(product.getId());

        newProduct.setName(product.getName());
        newProduct.setManufacturer(product.getManufacturer());
        newProduct.setDescription(product.getDescription());
        newProduct.setAmount(product.getAmount());
        newProduct.setValue(product.getValue());

        return this.productRepository.save(newProduct);
    }

    @Override
    public void delete(Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (Exception ex){
           throw new RuntimeException("Product not found ID -> ("+id+")");
        }
    }

    public Product fromDTO(@Valid ProductDTO productDTO){
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setValue(productDTO.getValue());
        product.setAmount(productDTO.getAmount());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setManufacturer(productDTO.getManufacturer());

        return product;
    }

    public Product fromRequest(@Valid ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());

        return product;
    }
}
