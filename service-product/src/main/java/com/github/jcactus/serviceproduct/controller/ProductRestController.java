package com.github.jcactus.serviceproduct.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jcactus.serviceproduct.dto.ProductDto;
import com.github.jcactus.serviceproduct.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody ProductDto product) {
        if (product == null
                || product.getId() != null
                || product.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (service.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ProductDto result = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<ProductDto> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ProductDto result = service.findById(id);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/existsAllById/{ids}", produces = "application/json")
    public ResponseEntity<Object> existsAllById(@PathVariable Set<Long> ids) {
        if (ids == null
                || ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return !service.existsAllById(ids) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(false) : ResponseEntity.status(HttpStatus.FOUND).body(true);
    }

    @GetMapping(value = "/existsById/{id}", produces = "application/json")
    public ResponseEntity<Object> existsById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return !service.existsById(id) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(false) : ResponseEntity.status(HttpStatus.FOUND).body(true);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateObject(@RequestBody ProductDto product) {
        if (product == null
                || product.getId() == null
                || product.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(product.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(product.getName(), product.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ProductDto result = service.updateObject(product);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParameters(@RequestBody ProductDto product) {
        if (product == null
                || product.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(product.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(product.getName(), product.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ProductDto result = service.updateParameters(product);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
