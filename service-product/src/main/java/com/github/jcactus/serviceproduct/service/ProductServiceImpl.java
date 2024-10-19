package com.github.jcactus.serviceproduct.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.jcactus.serviceproduct.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.github.jcactus.serviceproduct.model.Product;
import com.github.jcactus.serviceproduct.dto.ProductDto;
import com.github.jcactus.serviceproduct.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto save(ProductDto product) {
        return ProductMapper.toDto(repository.save(ProductMapper.toModel(product)));
    }

    @Override
    public List<ProductDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(long id) {
        return ProductMapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public ProductDto updateObject(ProductDto dto) {
        Product model = repository.findById(dto.getId()).orElse(null);
        if (model == null) {
            return null;
        }
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        return ProductMapper.toDto(repository.save(model));
    }

    @Override
    public ProductDto updateParameters(ProductDto dto) {
        Product model = repository.findById(dto.getId()).orElse(null);
        if (model == null) {
            return null;
        }
        if (dto.getName() != null) {
            model.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            model.setDescription(dto.getDescription());
        }
        return ProductMapper.toDto(repository.save(model));
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

    @Override
    public boolean existsAllById(Set<Long> ids) {
        return repository.existsAllById(ids);
    }

}
