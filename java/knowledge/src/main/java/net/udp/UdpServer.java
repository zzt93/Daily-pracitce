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

    public static final String END_SYMBOL = "\r\n\r\n";
    DatagramSocket server = new DatagramSocket(9000);
    private static final int SIZE = 100;

    public UdpServer() throws SocketException {
    }

    public void simpleReply() {
        byte[] buffer = new byte[SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, SIZE);
        while (true) {
            try {
                server.receive(packet);
                byte[] bytes = Arrays.copyOf(buffer, packet.getLength());
                System.out.println("server receive: " + new String(bytes));
                byte[] data = Bytes.concat("server received: ".getBytes(), bytes);
                server.send(new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort()));
                if (Arrays.equals(bytes, END_SYMBOL.getBytes())) {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
