package com.example;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.CommandLinePropertySource;

@SpringBootApplication
public class ReceiverApplication {
    private final static String queueName = "first";
//    @Value("${mq.rabbit.host}")
//    private String mqRabbitHost;
//    @Value("${mq.rabbit.port}")
//    private Integer mqRabbitPort;
//    @Value("${mq.rabbit.username}")
//    private String mqRabbitUsername;
//    @Value("${mq.rabbit.password}")
//    private String mqRabbitPassword;
//    @Value("${mq.rabbit.virtualHost}")
//    private String mqRabbitVirtualHost;

    /**
     * <ul>
     * <li>Create an appropriate {@link ApplicationContext} instance (depending on your
     * classpath)</li>
     * <li>Register a {@link CommandLinePropertySource} to expose command line arguments as
     * Spring properties</li>
     * <li>Refresh the application context, loading all singleton beans</li>
     * <li>Trigger any {@link CommandLineRunner} beans</li>
     * </ul>
     */
    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange", true, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory =
//                new CachingConnectionFactory(mqRabbitHost, mqRabbitPort);
//
//        connectionFactory.setUsername(mqRabbitUsername);
//        connectionFactory.setPassword(mqRabbitPassword);
//        connectionFactory.setVirtualHost(mqRabbitVirtualHost);
//
//        return connectionFactory;
//    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
