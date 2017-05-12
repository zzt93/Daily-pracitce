package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by zzt on 17/5/10.
 */
@Component
public class Receiver {

    private final ConfigurableApplicationContext context;

    public Receiver(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        if (message.equals("end")) {
            context.close();
        }
    }

}
