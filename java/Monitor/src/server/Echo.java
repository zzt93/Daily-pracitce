package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class Echo implements Runnable {

    private ServerSocket serverSocket;
    public static final int LISTEN_PORT = 10000;

    public Echo() throws IOException {
        // listen on this port
        serverSocket = new ServerSocket(LISTEN_PORT);
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("connected");
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println(inputLine);
            }
            // Closing this socket will also close the socket's InputStream and OutputStream.
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + LISTEN_PORT + " or listening for a connection");
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            executor.execute(new Echo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
