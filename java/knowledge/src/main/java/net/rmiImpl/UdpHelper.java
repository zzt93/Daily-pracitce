package net.rmiImpl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class UdpHelper {
    private final InetSocketAddress addr;
    private DatagramSocket client = new DatagramSocket();

    public UdpHelper(InetSocketAddress remoteAddr) throws SocketException {
        this.addr = remoteAddr;
    }

    public void send(byte[] data) throws IOException {
        DatagramPacket send = new DatagramPacket(data, data.length, addr);
        client.send(send);
    }

    public byte[] receive(int estimateSize) throws IOException {
        byte[] res = new byte[estimateSize];
        DatagramPacket receive = new DatagramPacket(res, estimateSize);
        client.receive(receive);
        return res;
    }

    public byte[] receiveTimeout(int estimateSize, int millisecond) throws IOException {
        byte[] res = new byte[estimateSize];
        DatagramPacket receive = new DatagramPacket(res, estimateSize);
        client.setSoTimeout(millisecond);
        client.receive(receive);
        return res;
    }
}
