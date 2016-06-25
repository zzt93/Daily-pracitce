package com.example.zzt.whyfi.common.net;

import android.support.annotation.WorkerThread;

import com.example.zzt.whyfi.model.Message;

import java.util.List;

/**
 * Created by zzt on 6/19/16.
 * <p>
 * Usage:
 */
@WorkerThread
public interface ConnectedChannel {

    @WorkerThread
    void read();

    @WorkerThread
    void readStr();

    @WorkerThread
    void readMsg();

    @WorkerThread
    void readList();

    void writeByte(byte[] bytes);

    void write(String message);

    boolean write(Message message);
    boolean write(List<Message> messages);


    void cancel();
}
