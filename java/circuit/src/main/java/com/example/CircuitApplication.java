package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableCircuitBreaker
//@EnableRetry
public class CircuitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitApplication.class, args);
	}
}
