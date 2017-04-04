package net.rmiImpl;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class UdpHelper {
    private static final int SERVER_PORT = 9000;
    private InetSocketAddress addr;
    private DatagramSocket socket;

    public UdpHelper(InetSocketAddress remoteAddr, boolean server) throws SocketException {
        if (server) {
            socket = new DatagramSocket(SERVER_PORT);
        } else {
            this.addr = remoteAddr;
            socket = new DatagramSocket();
        }
    }

    public void send(byte[] data) throws IOException {
        isClient();
        System.out.println(addr);
        DatagramPacket send = new DatagramPacket(data, data.length, addr);
        socket.send(send);
    }

    private void isClient() {
        if (addr == null) {
            throw new IllegalArgumentException("server should not call send");
        }
    }

    public Request receive(int estimateSize) throws IOException {
        byte[] res = new byte[estimateSize];
        DatagramPacket receive = new DatagramPacket(res, estimateSize);
        socket.receive(receive);
        return new Request(Arrays.copyOf(res, receive.getLength()),
                receive.getAddress(), receive.getPort());
    }

    public Request receiveTimeout(int estimateSize, int millisecond) throws IOException {
        byte[] res = new byte[estimateSize];
        DatagramPacket receive = new DatagramPacket(res, estimateSize);
        socket.receive(receive);
        return new Request(Arrays.copyOf(res, receive.getLength()),
                receive.getAddress(), receive.getPort());
    }


    public void serverSend(InetAddress host, int port, byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, host, port);
        socket.send(datagramPacket);
    }
}
