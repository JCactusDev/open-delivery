package com.github.jcactus.serviceorder.service;

import com.github.jcactus.serviceorder.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto order);

    List<OrderDto> findAll();

    OrderDto findById(long id);

    Long count();

    OrderDto updateObjectById(Long id, OrderDto order);

    OrderDto updateParametersById(Long id, OrderDto order);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsOrganizationById(String token, Long id);

    boolean existsClientById(String token, Long id);

    boolean existsAllProductById(String token, List<Long> ids);

}
