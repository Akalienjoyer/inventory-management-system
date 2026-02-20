package com.jara.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jara.inventory.model.Product;
import com.jara.inventory.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    return ResponseEntity.status(201).body(service.saveProduct(product));
}

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
        @PathVariable Long id,
        @Valid @RequestBody Product product) {

    return ResponseEntity.ok(service.updateProduct(id, product));
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}