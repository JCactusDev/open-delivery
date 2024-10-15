package com.github.jcactus.serviceorder.repository;

import com.github.jcactus.serviceorder.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
