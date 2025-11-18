package com.yzlaboratory.api_orders;

import com.yzlaboratory.api_orders.repository.OrdersRepository;
import com.yzlaboratory.api_orders.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ApiOrdersTests {

	@MockitoBean
	OrdersService service;

	@MockitoBean
	OrdersRepository repository;

	@Test
	void contextLoads() {
	}

}
