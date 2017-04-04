package net.rmiImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class NetworkHelper {
    private static final int DEFAULT_TIME_OUT = (int) TimeUnit.MILLISECONDS.convert(2, TimeUnit.SECONDS);
    private final UdpHelper udpHelper;

    public NetworkHelper(InetSocketAddress remoteAddr, boolean server) throws SocketException {
        udpHelper = new UdpHelper(remoteAddr, server);
    }

    public void clientSend(byte[] data) throws IOException {
        udpHelper.send(data);
    }

    public Request clientReceive(int interfaceMaxSize) throws IOException {
        return udpHelper.receiveTimeout(interfaceMaxSize, DEFAULT_TIME_OUT);
    }

    public Request serverReceive(int interfaceMaxSize) throws IOException {
        return udpHelper.receive(interfaceMaxSize);
    }

    public void serverSend(Request request, byte[] bytes) throws IOException {
        udpHelper.serverSend(request.getHost(), request.getPort(), bytes);
    }
}
