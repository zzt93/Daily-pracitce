package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by zzt on 17/3/23.
 */
public class UdpClient {

    DatagramSocket client = new DatagramSocket();
    private static final int SIZE = 100;

    public UdpClient() throws SocketException {
    }

    public void sendRev() {
        byte[] data = "asd".getBytes();
        DatagramPacket p = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 9000));
        try {
            System.out.println("client sending data: " + Arrays.toString(data));
            client.send(p);
            byte[] rcv = new byte[SIZE];
            DatagramPacket p1 = new DatagramPacket(rcv, SIZE);
            client.receive(p1);
            System.out.printf("client receive response: " + Arrays.toString(p1.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
