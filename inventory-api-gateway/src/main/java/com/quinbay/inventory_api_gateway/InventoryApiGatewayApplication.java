package com.quinbay.inventory_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.*;


@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApiGatewayApplication.class, args);
	}

}
