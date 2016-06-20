package com.example.zzt.whyfi.vm;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.example.zzt.whyfi.R;
import com.example.zzt.whyfi.common.NotificationHelper;
import com.example.zzt.whyfi.common.ThreadConfinement;
import com.example.zzt.whyfi.common.ToGuard;
import com.example.zzt.whyfi.common.net.ConnectedChannel;
import com.example.zzt.whyfi.common.net.MsgWriter;
import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;

import java.util.Collections;
import java.util.Iterator;
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
    private static final LinkedList<Message> sending = new LinkedList<>();
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


    public static void addReceived(final Message message) {
        synchronized (received) {
            received.addFirst(message);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                new NotificationHelper().showFixedNotification(R.string.msg_received_label);
            }
        });
    }

    @Override
    @UiThread
    @ToGuard("sending")
    public void writeMsg(Message message) {
        sent.addFirst(message);
        synchronized (sending) {
            sending.addFirst(message);
        }
    }

    @WorkerThread
    @Override
    @ToGuard("sending")
    public void performWrite(ConnectedChannel channel) {
        synchronized (sending) {
            for (Iterator<Message> iterator = sending.iterator(); iterator.hasNext();) {
                Message message = iterator.next();
                if (channel.write(message)) {
                    iterator.remove();
                }
            }
        }
    }
}
