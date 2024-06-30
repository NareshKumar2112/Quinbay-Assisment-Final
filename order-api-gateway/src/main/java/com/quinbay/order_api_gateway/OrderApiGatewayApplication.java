package com.quinbay.order_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiGatewayApplication.class, args);
	}

}
