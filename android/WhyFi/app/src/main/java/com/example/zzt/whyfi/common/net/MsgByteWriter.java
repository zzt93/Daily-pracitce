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
public class MsgByteWriter implements MsgWriter {
    private static final ArrayList<byte[]> messages = new ArrayList<>();
    private static final String CANONICAL_NAME = MsgByteWriter.class.getCanonicalName();


    @Override
    @ToGuard("messages")
    @UiThread
    public void writeMsg(Message message) {
        synchronized (messages) {
            try {
                messages.add(message.toBytes());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @ToGuard("messages")
    @WorkerThread
    public void performWrite(ConnectedChannel channel) {
        synchronized (messages) {
            Log.d(CANONICAL_NAME, "write message");
            for (byte[] message : messages) {
                channel.writeln(message);
            }
            //messages.clear();
        }
    }
}
