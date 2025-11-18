package com.yzlaboratory.api_orders.service;

import com.yzlaboratory.api_orders.entity.Order;
import com.yzlaboratory.api_orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository repository;

    // Konstruktor-Injektion
    public OrdersService(OrdersRepository repository) {
        this.repository = repository;
    }

    /**
     * Speichert eine neue Konfiguration in der RDS-Datenbank.
     */
    public Order saveOrder(Order config) {
        // Hier könnten Validierungen stattfinden
        return repository.save(config);
    }

    /**
     * Ruft alle Konfigurationen ab.
     */
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    /**
     * Findet eine Konfiguration nach ID.
     */
    public Optional<Order> getOrderById(UUID id) {
        return repository.findById(id);
    }
}