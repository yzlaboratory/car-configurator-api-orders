package com.yzlaboratory.api_orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class StatusController {

    @GetMapping("/status")
    public String status() {
        System.out.println("Status Controller called");
        return "<h1>Hello World, its me the Status Controller of your friend api-orders</h1>";
    }
}