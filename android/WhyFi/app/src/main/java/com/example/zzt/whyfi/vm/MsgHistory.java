package com.example.zzt.whyfi.vm;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.example.zzt.whyfi.common.NotificationHelper;
import com.example.zzt.whyfi.common.ThreadConfinement;
import com.example.zzt.whyfi.common.ToGuard;
import com.example.zzt.whyfi.common.net.ConnectedChannel;
import com.example.zzt.whyfi.common.net.MsgWriter;
import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 5/27/16.
 * <p/>
 * Usage:
 */
public class MsgHistory implements MsgWriter {

    @ThreadConfinement
    private static final LinkedList<Message> sent = new LinkedList<>();
    private static final LinkedList<Message> oneTimeSending = new LinkedList<>();
    private static final LinkedList<Message> received = new LinkedList<>();
    private static Handler handler = new Handler(Looper.getMainLooper());

    static {
        // TODO: 5/27/16 load from file/db
        sent.add(new Message(new Device("zzt"), "hello"));
        sent.add(new Message(new Device("hch"), "hello, world"));
        sent.add(new Message(new Device("hcw"), "we declared app:imageUrl in the layout and we passed the book image URL to it. Now we need to load the image URL, which can be done using the @BindingAdapter annotation. To do that,  we need to declare a static method annotated with @BindingAdapter and provide it with a parameter that corresponds to the custom attribute that we declared in the layout, i.e., imageURL. This method will be called as soon as the binding occurs."));

        synchronized (received) {
            received.add(new Message(new Device("xiao"), "we declared app:imageUrl in the layout and we passed the book image URL to it. Now we need to load the image URL, which can be done using the @BindingAdapter annotation. To do that,  we need to declare a static method annotated with @BindingAdapter and provide it with a parameter that corresponds to the custom attribute that we declared in the layout, i.e., imageURL. This method will be called as soon as the binding occurs."));
            received.add(new Message(new Device("cwr"), "hello, thank you"));
            received.add(new Message(new Device("bh"), "hello, bh"));
        }
    }

    public static List<Message> getSent() {
        return Collections.unmodifiableList(sent);
    }

    public static List<Message> getReceived() {
        return Collections.unmodifiableList(received);
    }

    @Deprecated
    public static void addReceived(final Message msg) {
        synchronized (received) {
            received.addFirst(msg);
        }
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                new NotificationHelper().showFixedNotification(R.string.msg_received_label);
//            }
//        });
    }

    public static void addReceived(final List<Message> messages) {
        if (messages.isEmpty()) {
            return;
        }
        synchronized (received) {
            received.addAll(0, messages);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = messages.get(0);
                String text = message.getDevice().getName() + ": " + message.getMessage();
                new NotificationHelper().showNotification(text, "WhyFi", text);
            }
        });
    }

    @Override
    @UiThread
    @ToGuard("oneTimeSending")
    public void writeMsg(Message message) {
        sent.addFirst(message);
        synchronized (oneTimeSending) {
            oneTimeSending.addFirst(message);
        }
    }

    @WorkerThread
    @Override
    @ToGuard("oneTimeSending")
    public void performWrite(ConnectedChannel channel) {
        LinkedList<Message> tmp;
        synchronized (oneTimeSending) {
            tmp = new LinkedList<>(oneTimeSending);
        }


        channel.write(tmp);

//        synchronized (oneTimeSending) {
//            oneTimeSending.retainAll(tmp);
//        }
    }
}
