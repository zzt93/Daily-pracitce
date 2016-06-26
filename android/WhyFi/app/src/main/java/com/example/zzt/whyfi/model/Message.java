package com.example.zzt.whyfi.model;

import com.example.zzt.whyfi.common.BytesSetting;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by zzt on 5/10/16.
 * <p/>
 * Usage:
 * The abstraction of content send/receive between devices
 */
public class Message implements Serializable {

    private final Device device;
    private final String message;
    private final String time;

    public Message(Device device, String message) {
        this(device, message, getCurrentTimeStamp());
    }

    public Message(Device device, String msg, String time) {
        this.device = device;
        this.message = msg;
        this.time = time;
    }

    public Device getDevice() {
        return device;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public static String getCurrentTimeStamp() {
        DateFormat sdfDate = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        Date now = new Date();
        return sdfDate.format(now);
    }

    @Deprecated
    public static Message getFromBytes(byte[] bytes) throws UnsupportedEncodingException {
        int i;
        for (i = bytes.length - 1; i >= 0; i--) {
            if (bytes[i] == BytesSetting.SPLIT_BYTE) {
                break;
            }
        }
        Device device = Device.getFromBytes(Arrays.copyOf(bytes, i));
        String msg = new String(bytes, i, bytes.length - i, BytesSetting.UTF_8);
        return new Message(device, msg);
    }

    @Deprecated
    public byte[] toBytes() throws UnsupportedEncodingException {
        byte[] bytes = device.toBytes();
        int devLen = bytes.length;
        byte[] res = new byte[devLen + 1 + message.length()];
        System.arraycopy(bytes, 0, res, 0, devLen);
        res[devLen] = BytesSetting.SPLIT_BYTE;
        System.arraycopy(message.getBytes(BytesSetting.UTF_8), 0, res, devLen + 1, message.length());
        return res;
    }

    @Override
    public String toString() {
        return device.toString() +
                BytesSetting.SPLIT_BYTE + message + BytesSetting.SPLIT_BYTE +
                time + BytesSetting.SPLIT_BYTE;
    }

    @Deprecated
    public static Message getFromChars(char[] chars) throws UnsupportedEncodingException {
        int i;
        for (i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == BytesSetting.SPLIT_BYTE) {
                break;
            }
        }
        Device device = Device.getFromChars(Arrays.copyOf(chars, i));
        String msg = new String(chars, i, chars.length - i);
        return new Message(device, msg);
    }
}
