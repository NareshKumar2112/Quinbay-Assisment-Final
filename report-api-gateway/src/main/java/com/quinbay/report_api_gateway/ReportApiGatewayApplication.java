package com.quinbay.report_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;

@SpringBootApplication
@EnableDiscoveryClient
public class ReportApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApiGatewayApplication.class, args);
	}

}
