package com.github.jcactus.serviceorder.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private Long organizationId;

    private Long clientId;

    private List<OrderPositionDto> positions;

}
