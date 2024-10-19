package com.github.jcactus.serviceorder.controller;

import com.github.jcactus.serviceorder.dto.OrderDto;
import com.github.jcactus.serviceorder.dto.OrderPositionDto;
import com.github.jcactus.serviceorder.model.OrderState;
import com.github.jcactus.serviceorder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService service;

    public OrderRestController(OrderService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(JwtAuthenticationToken jwtAuthenticationToken,
                                       @RequestBody OrderDto order) {
        String token = getTokenValue(jwtAuthenticationToken);
        if (order == null
                || order.getId() != null
                || order.getOrganizationId() == null
                || !service.existsOrganizationById(token, order.getOrganizationId())
                || order.getClientId() == null
                || !service.existsClientById(token, order.getClientId())
                || order.getPositions().isEmpty()
                || !service.existsAllProductById(token, order.getPositions().stream()
                .map(OrderPositionDto::getProductId)
                .collect(Collectors.toList()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        order.setState(OrderState.NEW.toString());
        OrderDto result = service.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<OrderDto> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        OrderDto result = service.findById(id);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/existsById/{id}", produces = "application/json")
    public ResponseEntity<Object> existsById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return !service.existsById(id) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(false) : ResponseEntity.status(HttpStatus.FOUND).body(true);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateObject(JwtAuthenticationToken jwtAuthenticationToken,
                                               @RequestBody OrderDto order) {
        String token = getTokenValue(jwtAuthenticationToken);
        if (order == null
                || order.getId() == null
                || order.getOrganizationId() == null
                || !service.existsOrganizationById(token, order.getOrganizationId())
                || order.getClientId() == null
                || !service.existsClientById(token, order.getClientId())
                || order.getPositions() == null
                || !service.existsAllProductById(token, order.getPositions().stream()
                .map(OrderPositionDto::getProductId)
                .collect(Collectors.toList()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(order.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        OrderDto result = service.updateObject(order);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParameters(JwtAuthenticationToken jwtAuthenticationToken,
                                                   @RequestBody OrderDto order) {
        String token = getTokenValue(jwtAuthenticationToken);
        if (order == null
                || order.getId() == null
                || (order.getOrganizationId() != null && !service.existsOrganizationById(token, order.getOrganizationId()))
                || (order.getClientId() != null && !service.existsClientById(token, order.getClientId()))
                || (order.getPositions() != null && !service.existsAllProductById(token, order.getPositions().stream()
                .map(OrderPositionDto::getProductId)
                .collect(Collectors.toList())))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(order.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        OrderDto result = service.updateParameters(order);
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

    private static String getTokenValue(JwtAuthenticationToken jwtAuthenticationToken) {
        return jwtAuthenticationToken.getToken().getTokenValue();
    }
}
