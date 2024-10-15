package com.github.jcactus.serviceproduct.repository;

import com.github.jcactus.serviceproduct.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Integer countAllByIdIn(Set<Long> ids);

    default boolean existsAllById(Set<Long> ids) {
        return countAllByIdIn(ids).equals(ids.size());
    }
}
