package com.yzlaboratory.api_orders.repository;

import com.yzlaboratory.api_orders.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends CrudRepository<Order, UUID> {

    List<Order> findAll();

    Order save(Order order);
}