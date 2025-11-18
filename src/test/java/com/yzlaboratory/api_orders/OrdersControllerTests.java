package com.yzlaboratory.api_orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzlaboratory.api_orders.entity.Order;
import com.yzlaboratory.api_orders.controller.OrdersController;
import com.yzlaboratory.api_orders.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdersController.class)
public class OrdersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdersService ordersService;

    @Autowired
    private ObjectMapper objectMapper; // Zum Umwandeln von Objekten in JSON

    // --- 1. Test für den Status-Endpunkt ---
    @Test
    void testStatus() throws Exception {
        mockMvc.perform(get("/orders/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("<h1>Hello World, its me the Status Controller of your friend api-orders</h1>"));
    }

    @Test
    void testGetOrders() throws Exception {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setConfigurationId(UUID.randomUUID().toString());
        List<Order> orderList = List.of(order);

        when(ordersService.getAllOrders()).thenReturn(orderList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(order.getId().toString())))
                .andExpect(jsonPath("$[0].configurationId", is(order.getConfigurationId())));
    }

    @Test
    void testGetOrders_Empty() throws Exception {
        when(ordersService.getAllOrders()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testCreateOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setConfigurationId(UUID.randomUUID().toString());

        Order savedOrder = new Order();
        savedOrder.setId(UUID.randomUUID());
        savedOrder.setConfigurationId(newOrder.getConfigurationId());

        when(ordersService.saveOrder(any(Order.class))).thenReturn(savedOrder);

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newOrder)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(savedOrder.getId().toString())))
                .andExpect(jsonPath("$.configurationId", is(savedOrder.getConfigurationId())));
    }

    @Test
    void testGetOrderById_Found() throws Exception {
        UUID orderId = UUID.randomUUID();
        Order foundOrder = new Order();
        foundOrder.setId(orderId);

        when(ordersService.getOrderById(orderId)).thenReturn(Optional.of(foundOrder));

        mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(orderId.toString())));
    }

    @Test
    void testGetOrderById_NotFound() throws Exception {
        UUID orderId = UUID.randomUUID();

        when(ordersService.getOrderById(orderId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isNotFound());
    }
}