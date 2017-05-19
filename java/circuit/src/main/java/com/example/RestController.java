package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zzt on 5/3/17.
 * <p>
 * <h3></h3>
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final FooService service;

    @Autowired
    public RestController(FooService service) {
        this.service = service;
    }

    @GetMapping("/boom")
    public String boom() throws Exception {
        return service.number();
    }
}
