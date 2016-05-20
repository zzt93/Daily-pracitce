package com.example.zzt.whyfi.model;

import android.media.Image;
import android.os.Build;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * Usage:
 */
public class Device {

    private static final String model = Build.MODEL;
    private final String name;
    private Image avatar;

    public Device(String name) {
        this.name = name;
    }

    public Device() {
        name = model;
    }

    public String getName() {
        return name;
    }
}
