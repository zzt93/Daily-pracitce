package com.example.zzt.whyfi.common.net;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.example.zzt.whyfi.common.ToGuard;
import com.example.zzt.whyfi.model.Message;

/**
 * Created by zzt on 6/19/16.
 * <p/>
 * Usage:
 */
public interface MsgWriter {
    @ToGuard("messages")
    @UiThread
    void writeMsg(Message message);

    @ToGuard("messages")
    @WorkerThread
    void performWrite(ConnectedChannel channel);
}
