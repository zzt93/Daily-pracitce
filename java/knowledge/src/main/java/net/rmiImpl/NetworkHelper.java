package net.rmiImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class NetworkHelper {
    private static final int DEFAULT_TIME_OUT = (int) TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private final UdpHelper udpHelper;

    public NetworkHelper(InetSocketAddress remoteAddr) throws SocketException {
        udpHelper = new UdpHelper(remoteAddr);
    }

    public void send(byte[] data) throws IOException {
        udpHelper.send(data);
    }

    public byte[] receive(int interfaceMaxSize) throws IOException {
        byte[] bytes = new byte[interfaceMaxSize];
        udpHelper.receiveTimeout(interfaceMaxSize, DEFAULT_TIME_OUT);
        return bytes;
    }
}
