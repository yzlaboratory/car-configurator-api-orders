package com.yzlaboratory.api_orders.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(name="configuration_id", nullable = false)
    private String configurationId;

    @Column(name = "order_timestamp")
    private OffsetDateTime  orderTimestamp;

    @Column()
    private String status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime  updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.from(Instant.now());
        this.updatedAt = OffsetDateTime.from(Instant.now());
        this.orderTimestamp = OffsetDateTime.from(Instant.now());
        this.status = "pending";
    }

    public Order() {}

    public Order(String configurationId) {
        this.configurationId = configurationId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public OffsetDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(OffsetDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
