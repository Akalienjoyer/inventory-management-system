package com.jara.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jara.inventory.exception.ResourceNotFoundException;
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

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setPrice(updatedProduct.getPrice());

        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product existing = getProductById(id);
        repository.delete(existing);
    }
}