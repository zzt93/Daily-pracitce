package com.example.zzt.whyfi.common.net;

import com.example.zzt.whyfi.model.Message;

import java.util.ArrayList;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
public class MsgStrWriter implements MsgWriter {
    private static final ArrayList<String> messages = new ArrayList<>();

    @Override
    public void writeMsg(Message message) {
        synchronized (messages) {
           messages.add(message.toString());
        }
    }

    @Override
    public void performWrite(ConnectedChannel channel) {
        synchronized (messages) {
            for (String message : messages) {
                channel.write(message);
            }
        }
    }
}
