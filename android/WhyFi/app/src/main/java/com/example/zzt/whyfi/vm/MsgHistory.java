package com.example.zzt.whyfi.vm;

import com.example.zzt.whyfi.model.Device;
import com.example.zzt.whyfi.model.Message;

import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 5/27/16.
 * <p/>
 * Usage:
 */
public class MsgHistory {

    private static LinkedList<Message> sent = new LinkedList<>();
    private static LinkedList<Message> received = new LinkedList<>();

    static {
        // TODO: 5/27/16 load from file/db
        sent.add(new Message(new Device("zzt"), "hello"));
        sent.add(new Message(new Device("hch"), "hello, world"));
        sent.add(new Message(new Device("hcw"), "we declared app:imageUrl in the layout and we passed the book image URL to it. Now we need to load the image URL, which can be done using the @BindingAdapter annotation. To do that,  we need to declare a static method annotated with @BindingAdapter and provide it with a parameter that corresponds to the custom attribute that we declared in the layout, i.e., imageURL. This method will be called as soon as the binding occurs."));

        received.add(new Message(new Device("xiao"), "we declared app:imageUrl in the layout and we passed the book image URL to it. Now we need to load the image URL, which can be done using the @BindingAdapter annotation. To do that,  we need to declare a static method annotated with @BindingAdapter and provide it with a parameter that corresponds to the custom attribute that we declared in the layout, i.e., imageURL. This method will be called as soon as the binding occurs."));
        received.add(new Message(new Device("cwr"), "hello, thank you"));
        received.add(new Message(new Device("bh"), "hello, bh"));
    }

    public static List<Message> getSent() {
        return Collections.unmodifiableList(sent);
    }

    public static List<Message> getReceived() {
        return Collections.unmodifiableList(received);
    }

    public static void addSent(Message message) {
        sent.addFirst(message);
    }
}
