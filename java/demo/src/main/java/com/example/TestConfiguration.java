package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zzt on 5/3/17.
 * <p>
 * <h3></h3>
 */
@Configuration
public class TestConfiguration {

    @Bean
    public BarService barService() {
        return new BarService();
    }
}
