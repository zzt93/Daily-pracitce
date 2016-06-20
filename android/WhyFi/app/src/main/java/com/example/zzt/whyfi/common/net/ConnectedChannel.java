package com.example.zzt.whyfi.common.net;

import android.support.annotation.WorkerThread;

import com.example.zzt.whyfi.model.Message;

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
    void readObj();

    void writeByte(byte[] bytes);

    void write(String message);

    boolean write(Message message);

    void cancel();
}
