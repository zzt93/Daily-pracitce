package com.example.zzt.whyfi.common.net;

/**
 * Created by zzt on 6/19/16.
 * <p>
 * Usage:
 */
public interface ConnectedChannel {

    void writeln(byte[] bytes);

    void cancel();

    void write(String message);
}
