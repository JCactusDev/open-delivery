package com.github.jcactus.serviceorder.mapper;

import com.github.jcactus.serviceorder.dto.OrderDto;
import com.github.jcactus.serviceorder.dto.OrderPositionDto;
import com.github.jcactus.serviceorder.model.Order;
import com.github.jcactus.serviceorder.model.OrderPosition;
import com.github.jcactus.serviceorder.model.OrderState;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toModel(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order model = new Order();
        model.setId(dto.getId());
        model.setState(Enum.valueOf(OrderState.class, dto.getState()));
        model.setOrganizationId(dto.getOrganizationId());
        model.setClientId(dto.getClientId());
        model.setPositions(
                dto.getPositions().stream()
                        .map(positions -> {
                                    OrderPosition position = new OrderPosition();
                                    position.setId(positions.getId());
                                    position.setProductId(positions.getProductId());
                                    position.setPrice(positions.getPrice());
                                    position.setCount(positions.getCount());
                                    position.setOrder(model);
                                    return position;
                                }
                        ).collect(Collectors.toList())
        );
        return model;
    }

    public static OrderDto toDto(Order model) {
        if (model == null) {
            return null;
        }
        OrderDto dto = new OrderDto();
        dto.setId(model.getId());
        dto.setState(model.getState());
        dto.setOrganizationId(model.getOrganizationId());
        dto.setClientId(model.getClientId());
        dto.setPositions(
                model.getPositions().stream()
                        .map(positions -> {
                            OrderPositionDto position = new OrderPositionDto();
                            position.setId(positions.getId());
                            position.setProductId(positions.getProductId());
                            position.setPrice(positions.getPrice());
                            position.setCount(positions.getCount());
                            return position;
                        })
                        .collect(Collectors.toList())
        );
        return dto;
    }

}
