package connection;

import socketWay.server.SocketServer;

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
public class EchoServer implements Runnable {

    private WebConnection serverSocket;

    public EchoServer() throws IOException {
        // listen on this port
//        serverSocket = ConnectionFactory.getSocketServer();
        serverSocket = ConnectionFactory.getServerDatagram();
    }

    @Override
    public void run() {
        try {
            PrintWriter out = serverSocket.getOut();
            BufferedReader in = serverSocket.getIn();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println(inputLine);
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + SocketServer.LISTEN_PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            executor.execute(new EchoServer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
