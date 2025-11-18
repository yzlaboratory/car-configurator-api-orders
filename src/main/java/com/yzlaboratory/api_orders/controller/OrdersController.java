package com.yzlaboratory.api_orders.controller;

import com.yzlaboratory.api_orders.entity.Order;
import com.yzlaboratory.api_orders.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService service;

    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @GetMapping("/status")
    public String status() {
        System.out.println("Status Controller called");
        return "<h1>Hello World, its me the Status Controller of your friend api-orders</h1>";
    }

    @PostMapping()
    public ResponseEntity<Order> postConfig(@RequestBody Order order) {
        //fill orderid
        //this.dynamoDbService.saveConfig(config);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> createConfiguration(@RequestBody Order order) {
        Order savedConfig = service.saveOrder(order);
        return new ResponseEntity<>(savedConfig, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getConfigurationById(@PathVariable UUID id) {
        return service.getOrderById(id)
                .map(config -> new ResponseEntity<>(config, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}