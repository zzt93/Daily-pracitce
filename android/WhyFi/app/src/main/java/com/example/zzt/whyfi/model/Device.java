package com.example.zzt.whyfi.model;

import android.os.Build;

/**
 * Created by zzt on 5/20/16.
 * <p/>
 * Usage:
 */
public class Device {

    private static final String model = Build.MODEL;
    public static final Device now = new Device("this phone", android.R.drawable.sym_def_app_icon,
            "a boy want to make success by his hand");

    private final String name;
    private int avatar;
    private String des;

    public Device(String name) {
        this.name = name;
    }

    public Device() {
        name = model;
    }

    public Device(String name, int id, String des) {
        this(name);
        avatar = id;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getDes() {
        return des;
    }
}
