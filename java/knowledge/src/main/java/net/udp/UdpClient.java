package net.udp;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 17/3/23.
 */
public class UdpClient {

    public static final String END_SYMBOL = "\r\n\r\n";
    public static final int TIMEOUT = 1000000;
    DatagramSocket client = new DatagramSocket();
    private static final int SIZE = 100;


    private final Scanner scanner;

    public UdpClient() throws SocketException {
        scanner = new Scanner(System.in);
    }

    public void sendRev() {
        InetSocketAddress localhost = new InetSocketAddress("localhost", 9000);
        while (scanner.hasNext()) {
            byte[] data = scanner.next().getBytes();
            DatagramPacket p = new DatagramPacket(data, data.length, localhost);
            try {
                System.out.println("client sending data: " + Arrays.toString(data));
                client.send(p);
                byte[] rcv = new byte[SIZE];
                DatagramPacket response = new DatagramPacket(rcv, SIZE);
                client.setSoTimeout(TIMEOUT);
                client.receive(response);
                System.out.printf("client receive response: " + new String(response.getData()).substring(0, response.getLength()));
            } catch (SocketTimeoutException timeout) {
                System.err.println("Fail to receive response in: " + TimeUnit.MICROSECONDS.toSeconds(TIMEOUT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // special end symbol
        byte[] data = END_SYMBOL.getBytes();
        try {
            client.send(new DatagramPacket(data, data.length, localhost));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
