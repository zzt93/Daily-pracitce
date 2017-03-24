package net.udp;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 17/3/23.
 */
public class UdpServer {

    DatagramSocket server = new DatagramSocket(9000);
    private static final int SIZE = 100;

    public UdpServer() throws SocketException {
    }

    public void simpleReply() {
        byte[] bytes = new byte[SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, SIZE);
        try {
            server.receive(packet);
            System.out.println("server receive: " + Arrays.toString(bytes));
            byte[] data = Bytes.concat("server received:".getBytes(), packet.getData());
            server.send(new DatagramPacket(data, packet.getLength(), packet.getAddress(), packet.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SocketException {
        UdpServer udpServer = new UdpServer();
        UdpClient udpClient = new UdpClient();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(udpClient::sendRev);
        service.execute(udpServer::simpleReply);
        service.shutdown();
    }
}
