package com.github.jcactus.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.github.jcactus.model.Product;
import com.github.jcactus.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList());
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Product updateObjectById(Long id, Product product) {
        Product updatedProduct = repository.findById(id).orElse(null);
        if (updatedProduct == null) {
            return null;
        }
        updatedProduct = product.clone();
        updatedProduct.setId(id);
        return repository.save(updatedProduct);
    }

    @Override
    public Product updateParametersById(Long id, Product product) {
        Product updatedProduct = repository.findById(id).orElse(null);
        if (updatedProduct == null) {
            return null;
        }
        if (product.getName() != null) {
            updatedProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            updatedProduct.setDescription(product.getDescription());
        }
        return repository.save(updatedProduct);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return repository.existsByNameAndIdNot(name, id);
    }

}
