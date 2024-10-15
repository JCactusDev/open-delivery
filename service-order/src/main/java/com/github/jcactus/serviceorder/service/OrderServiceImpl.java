package com.github.jcactus.serviceorder.service;

import com.github.jcactus.serviceorder.dto.OrderDto;
import com.github.jcactus.serviceorder.mapper.OrderMapper;
import com.github.jcactus.serviceorder.model.Order;
import com.github.jcactus.serviceorder.model.OrderPosition;
import com.github.jcactus.serviceorder.repository.OrderRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final WebClient webClient;

    private final DiscoveryClient discoveryClient;

    public OrderServiceImpl(OrderRepository repository, WebClient webClient, DiscoveryClient discoveryClient) {
        this.repository = repository;
        this.webClient = webClient;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public OrderDto save(OrderDto client) {
        return OrderMapper.toDto(repository.save(OrderMapper.toModel(client)));
    }

    @Override
    public List<OrderDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(long id) {
        return OrderMapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public OrderDto updateObjectById(Long id, OrderDto dto) {
        Order model = repository.findById(id).orElse(null);
        if (model == null) {
            return null;
        }
        model.setId(dto.getId());
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
        return OrderMapper.toDto(repository.save(model));
    }

    @Override
    public OrderDto updateParametersById(Long id, OrderDto dto) {
        Order model = repository.findById(id).orElse(null);
        if (model == null) {
            return null;
        }
        if (dto.getOrganizationId() != null) {
            model.setOrganizationId(dto.getOrganizationId());
        }
        if (dto.getClientId() != null) {
            model.setClientId(dto.getClientId());
        }
        if (dto.getPositions() != null) {
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
        }
        return OrderMapper.toDto(repository.save(model));
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
    public boolean existsOrganizationById(String token, Long id) {
        if (id == null) {
            return false;
        }
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-organization").get(0);
        return Boolean.TRUE.equals(webClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/organizations/existsById/" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(Boolean.class)
                .defaultIfEmpty(Boolean.FALSE)
                .block());
    }

    @Override
    public boolean existsClientById(String token, Long id) {
        if (id == null) {
            return false;
        }
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-client").get(0);
        return Boolean.TRUE.equals(webClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/clients/existsById/" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(Boolean.class)
                .defaultIfEmpty(Boolean.FALSE)
                .block());
    }

    @Override
    public boolean existsAllProductById(String token, List<Long> ids) {
        if (ids == null
                || ids.isEmpty()) {
            return false;
        }
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
        return Boolean.TRUE.equals(webClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/products/existsAllById/" + ids.stream().map(Object::toString).collect(Collectors.joining(",")))
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(Boolean.class)
                .defaultIfEmpty(Boolean.FALSE)
                .block());
    }

}
