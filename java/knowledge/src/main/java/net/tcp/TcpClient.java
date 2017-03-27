package net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zzt on 17/3/24.
 */
public class TcpClient {

    private static final int MAX = 1000;
    private Socket client = new Socket();
    private final Scanner scanner;

    public TcpClient() throws IOException {
        client.connect(new InetSocketAddress("localhost", 9000));
        scanner = new Scanner(System.in);
    }

    void sendRcv() throws IOException {
        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();
        while (scanner.hasNext()) {
            String input = scanner.next();
            System.out.println("client sending: " + input);
            outputStream.write(input.getBytes());
            byte[] bytes = new byte[MAX];
            int read = inputStream.read(bytes);
            if (read >= 0) {
                byte[] response = Arrays.copyOf(bytes, read);
                System.out.println(new String(response));
            }
        }
    }

    void send() {
        try {
            sendRcv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
