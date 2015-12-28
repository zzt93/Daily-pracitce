package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class PingClient implements Runnable {

    public static final String PING = "ping";
    public static final int WAIT = 1000;
    private PrintWriter out;
    private BufferedReader in;

    public PingClient() {
        WebConnection localhost;
        try {
            // change it here when use different connection method
//            localhost = ConnectionFactory.getSocketClientConnection();
//            localhost = ConnectionFactory.getServerUrl();
            localhost = ConnectionFactory.getClientDatagram();
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
            // no need to add this, println will auto flush
            // out.flush();
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
                    throw new RuntimeException("fail to receive right message: " + s);
                } else {
                    System.out.println("Client: ping success");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new PingClient());
    }
}
