package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class Ping implements Runnable {

    public static final String PING = "ping";
    public static final int WAIT = 1000;
    public static final int DEFAULT_PORT = 10000;
    private PrintWriter out;
    private BufferedReader in;

    public Ping() {
        SocketConnection localhost;
        try {
            localhost = new SocketConnection(SocketConnection.LOCALHOST, DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        out = localhost.getOut();
        in = localhost.getIn();
    }

    @Override
    public void run() {
        while (true) {
            out.println(PING);
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (!in.ready()) {
                    throw new RuntimeException("fail to receive return message");
                }
                String s = in.readLine();
                if (s == null) {
                    throw new RuntimeException("fail to receive return message");
                }
                boolean equals = s.equals(PING);
                if (!equals) {
                    throw new RuntimeException("fail to receive return message");
                } else {
                    System.out.println("success");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
