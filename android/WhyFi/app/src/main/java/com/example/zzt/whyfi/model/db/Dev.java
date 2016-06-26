package com.example.zzt.whyfi.model.db;

import com.example.zzt.whyfi.common.DeviceUuidFactory;

import java.util.UUID;

/**
 * Created by zzt on 6/26/16.
 * <p/>
 * Usage:
 */
public class Dev {

    private static DeviceUuidFactory factory = new DeviceUuidFactory();
    private String did = factory.getDeviceUuid().toString();
    private String name;
    private String des;
    private String avatar;

    public Dev(String name, String des, String avatar) {
        this.name = name;
        this.des = des;
        this.avatar = avatar;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static String getGUID() {
        return UUID.randomUUID().toString();
    }
}
