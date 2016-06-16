package com.example.zzt.whyfi.common.net;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.example.zzt.whyfi.common.ToGuard;
import com.example.zzt.whyfi.model.Message;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by zzt on 6/11/16.
 * <p/>
 * Usage:
 */
public class BTMsgWriter {
    private static final ArrayList<byte[]> messages = new ArrayList<>();
    private static final String CANONICAL_NAME = BTMsgWriter.class.getCanonicalName();


    @ToGuard("messages")
    @UiThread
    public static void writeMsg(Message message) {
        synchronized (messages) {
            try {
                messages.add(message.toBytes());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @ToGuard("messages")
    @WorkerThread
    public static void performWrite(ConnectedBT connectedBT) {
        synchronized (messages) {
            Log.d(CANONICAL_NAME, "write message");
            for (byte[] message : messages) {
                connectedBT.writeln(message);
            }
            //messages.clear();
        }
    }
}
