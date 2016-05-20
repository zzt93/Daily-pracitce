package com.example.zzt.whyfi.model;

import java.util.Date;

/**
 * Created by zzt on 5/10/16.
 * <p/>
 * Usage:
 * The abstraction of content send/receive between devices
 */
public class Message {

    private final Device device;
    private final String message;
    private final String time;

    public Message(Device device, String message) {
        this.device = device;
        this.message = message;
        time = new Date().toString();
    }

    public Device getDevice() {
        return device;
    }

    public String getMessage() {
        return message;
    }
}
