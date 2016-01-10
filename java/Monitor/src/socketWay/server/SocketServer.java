package socketWay.server;

import connection.WebConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class SocketServer implements WebConnection {
    public static final int LISTEN_PORT = 10000;


    private final PrintWriter out;
    private final BufferedReader in;
    private final Socket accept;

    public SocketServer(int listenPort) throws IOException {
        // listen on this port
        ServerSocket serverSocket = new ServerSocket(listenPort);
        accept = serverSocket.accept();
        System.out.println("connected");
        out = new PrintWriter(new OutputStreamWriter(accept.getOutputStream(), "utf8"), true);
        in = new BufferedReader(
                new InputStreamReader(accept.getInputStream(), "utf8"));
    }

    @Override
    public PrintWriter getOut() {
        return out;
    }

    @Override
    public BufferedReader getIn() {
        return in;
    }

    @Override
    public void close() throws IOException {
        // Closing this socket will also close the socket's InputStream and OutputStream.
        accept.close();
    }
}
