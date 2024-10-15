package com.github.jcactus.serviceproduct.service;

import com.github.jcactus.serviceproduct.dto.ProductDto;

import java.util.List;
import java.util.Set;

public interface ProductService {

    ProductDto save(ProductDto product);

    List<ProductDto> findAll();

    ProductDto findById(long id);

    Long count();

    ProductDto updateObjectById(Long id, ProductDto product);

    ProductDto updateParametersById(Long id, ProductDto product);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsAllById(Set<Long> ids);

}
