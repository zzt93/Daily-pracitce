package com.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by zzt on 17/5/10.
 */
@Component
public class Sender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    public Sender(RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.printf("Sending %dth message...\n", i);
            rabbitTemplate.convertAndSend(SenderApplication.queueName, "test " + i);
        }
        rabbitTemplate.convertAndSend(SenderApplication.queueName, "end");
        context.close();
    }
}
