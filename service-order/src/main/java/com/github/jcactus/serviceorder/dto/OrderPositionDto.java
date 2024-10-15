package com.github.jcactus.serviceorder.dto;

import lombok.Data;

@Data
public class OrderPositionDto {

    private Long id;

    private Long productId;

    private double count;

    private double price;

}
