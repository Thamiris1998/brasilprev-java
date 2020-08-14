package com.application.service;

import com.application.dao.ProductRepository;
import com.application.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findOne(String id) {
        return repository.findById(id);
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public Product create(Product product) {
       return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }
}
