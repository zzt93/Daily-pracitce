package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by zzt on 17/6/29.
 */
public class TestSchedule {

    @Scheduled(fixedRate = 200)
    public void doSomething() {
        System.out.println("??????");
    }
}
