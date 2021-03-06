package com.zzt.learnspring.web;


import com.zzt.learnspring.service.HelloWorldService;
import com.zzt.learnspring.service.UpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by zzt on 7/17/16.
 * <p>
 * <h3></h3>
 */
@Controller
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private final HelloWorldService helloWorldService;
    private final UpdateService updateService;
    private final ApplicationContext context;

    @Autowired
    public WelcomeController(HelloWorldService helloWorldService, UpdateService updateService, ApplicationContext context) {
        this.helloWorldService = helloWorldService;
        this.updateService = updateService;
        this.context = context;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        logger.debug("index() is executed!");


        model.put("title", helloWorldService.getTitle(""));
        model.put("msg", helloWorldService.getDesc());

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        logger.debug("hello() is executed - $name {}", name);

        context.getMessage("action.add.success", new Object[]{"asdf"}, Locale.US);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", helloWorldService.getTitle(name));
        model.addObject("msg", helloWorldService.getDesc());
        updateService.update(new Random(1).nextInt(10) - 5);

        return model;
    }

}
