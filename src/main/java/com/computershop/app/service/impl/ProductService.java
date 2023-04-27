package com.computershop.app.service.impl;

import com.computershop.app.model.Product;
import com.computershop.app.repository.ProductRepository;
import com.computershop.app.service.CrudService;
import com.computershop.app.service.exceptions.DataBindingViolationException;
import com.computershop.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService implements CrudService<Product>{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(()-> new ObjectNotFoundException("Product Not Found! ID -> "+id));
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
        Product product = this.findById(id);
        try {
            this.productRepository.delete(product);
        } catch (Exception ex){
            throw new DataBindingViolationException("Cannot delete, the entity have relationships");
        }
    }
}
