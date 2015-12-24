package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class Echo implements Runnable {

    private ServerSocket serverSocket;
    public static final int PORT_NUMBER = 9000;

    public Echo() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);

    }

    @Override
    public void run() {

        try {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + PORT_NUMBER + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
