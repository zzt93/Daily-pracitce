package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@SpringBootApplication
@EnableScheduling
public class ScheduleApplication implements CommandLineRunner {

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;
    @Autowired
    private TestSchedule test;

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, "--debug").close();
    }

    @Bean
    TestSchedule test() {
        return new TestSchedule();
    }

    @Override
    public void run(String... strings) throws Exception {
        Thread.sleep(1000);
        postProcessor.postProcessBeforeDestruction(test, "testSchedule");
    }
}
