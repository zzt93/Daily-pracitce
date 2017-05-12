package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenderApplication {

	public static String queueName = "first";

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}
}
