package com.github.jcactus.serviceproduct.mapper;

import com.github.jcactus.serviceproduct.dto.ProductDto;
import com.github.jcactus.serviceproduct.model.Product;

public class ProductMapper {

    public static Product toModel(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product model = new Product();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static ProductDto toDto(Product model) {
        if (model == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        return dto;
    }
}
