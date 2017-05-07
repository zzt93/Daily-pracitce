package com.example;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

/**
 * Created by zzt on 5/3/17.
 * <p>
 * <h3></h3>
 */
@Service
public class FooService {

//    @HystrixCommand(fallbackMethod = "fallBack")
//    @Retryable(include = BoomException.class)
    @CircuitBreaker(include = BoomException.class, openTimeout = 5, resetTimeout = 25, maxAttempts = 1)
    public String number() throws Exception {
        if (Math.random() > .5) {
            Thread.sleep(1000 * 2);
            System.err.println("calling");
            throw new BoomException("Boom");
        }
        return MethodHandles.lookup().lookupClass().toString();
    }

    @Recover
    public String fallBack() {
        return "this is fall back";
    }
}
