package com.github.jcactus.service;

import com.github.jcactus.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findById(long id);

    Long count();

    Product updateObjectById(Long id, Product product);

    Product updateParametersById(Long id, Product product);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
