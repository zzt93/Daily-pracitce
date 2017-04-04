package net.rmiImpl;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class Request {

    private byte[] data;
    private InetAddress host;
    private int port;
    private String request;

    public Request(byte[] data, InetAddress host, int port) {
        this.data = data;
        this.host = host;
        this.port = port;
    }

    public byte[] getData() {
        return data;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Request{" +
                "data=" + Arrays.toString(data) +
                ", host=" + host +
                ", port=" + port +
                '}';
    }
}
