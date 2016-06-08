package com.example.zzt.whyfi.model;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.os.Bundle;

import com.example.zzt.whyfi.BR;
import com.example.zzt.whyfi.common.BytesSetting;

import java.io.UnsupportedEncodingException;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * Usage:
 */
public class Device extends BaseObservable {

    private static final String model = Build.MODEL;
    public static final int SYM_DEF_APP_ICON = android.R.drawable.sym_def_app_icon;
    public static final Device now = new Device("this phone: " + model, SYM_DEF_APP_ICON,
            "a boy want to make success by his hand");
    public static final String intentName = "device name";
    public static final String intentAvatar = "device avatar";
    public static final String intentDes = "device des";

    private String name;
    private int avatar;
    private String des;

    public Device(String name) {
        this.name = name;
    }


    public Device(String name, int id, String des) {
        this(name);
        avatar = id;
        this.des = des;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public int getAvatar() {
        return avatar;
    }

    @Bindable
    public String getDes() {
        return des;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public byte[] getNameByte() throws UnsupportedEncodingException {
        return name.getBytes(BytesSetting.UTF_8);
    }

    public static String getNameFromByte(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, BytesSetting.UTF_8);
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    public void setDes(String des) {
        this.des = des;
        notifyPropertyChanged(BR.des);
    }

    public void addToIntent(Intent intent) {
        intent.putExtra(intentName, name);
        intent.putExtra(intentAvatar, avatar);
        intent.putExtra(intentDes, des);
    }

    public static Device getFromIntent(Intent intent) {
        return new Device(intent.getStringExtra(intentName),
                intent.getIntExtra(intentAvatar, SYM_DEF_APP_ICON),
                intent.getStringExtra(intentDes));
    }

    public void addToBundle(Bundle bundle) {
        bundle.putString(intentName, name);
        bundle.putInt(intentAvatar, avatar);
        bundle.putString(intentDes, des);
    }

    public static Device getFromBundle(Bundle bundle) {
        return new Device(bundle.getString(intentName),
                bundle.getInt(intentAvatar),
                bundle.getString(intentDes));
    }

    public static Device getFromBytes(byte[] bytes) throws UnsupportedEncodingException {
        int nameLen = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == BytesSetting.SPLIT_BYTE) {
                nameLen = i;
                break;
            }
        }
        String name = new String(bytes, 0, nameLen, BytesSetting.UTF_8);
        String des = new String(bytes, nameLen + 1, bytes.length - nameLen - 1, BytesSetting.UTF_8);
        return new Device(name, SYM_DEF_APP_ICON, des);
    }

    public byte[] toBytes() throws UnsupportedEncodingException {
        int nameLen = name.length();
        int desLen = des.length();
        byte[] bytes = new byte[nameLen + 1 + desLen];

        System.arraycopy(name.getBytes(BytesSetting.UTF_8), 0, bytes, 0, nameLen);
        bytes[nameLen] = BytesSetting.SPLIT_BYTE;

        System.arraycopy(des.getBytes(BytesSetting.UTF_8), 0, bytes, nameLen + 1, desLen);

        return bytes;
    }
}
