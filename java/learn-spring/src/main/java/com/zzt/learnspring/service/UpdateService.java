package com.zzt.learnspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zzt on 7/18/16.
 * <p>
 * <h3></h3>
 */
@Service
public class UpdateService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    public boolean update(int a) {
        logger.debug("update({}) is executed", a);
        return a > 0;
    }
}
