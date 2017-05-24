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

    private void receiveMessage(String message) throws InterruptedException {
        System.out.println("Received <" + message + ">");
        if (message.equals("end")) {
            // if close immediately, then last message will be consume multiple time
            // may be because no ack is sent back?
//            Thread.sleep(1000);
//            context.close();
        }
    }

}
