package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenderApplication {

	public static String queueName = "first";
//	@Value("${mq.rabbit.host}")
//	private String mqRabbitHost;
//	@Value("${mq.rabbit.port}")
//	private Integer mqRabbitPort;
//	@Value("${mq.rabbit.username}")
//	private String mqRabbitUsername;
//	@Value("${mq.rabbit.password}")
//	private String mqRabbitPassword;
//	@Value("${mq.rabbit.virtualHost}")
//	private String mqRabbitVirtualHost;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory =
//				new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);
//
//		connectionFactory.setUsername(mqRabbitUsername);
//		connectionFactory.setPassword(mqRabbitPassword);
//		connectionFactory.setVirtualHost(mqRabbitVirtualHost);
//
//		return connectionFactory;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}
}
