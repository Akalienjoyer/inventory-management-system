package com.jara.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jara.inventory.model.Product;
import com.jara.inventory.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }
}