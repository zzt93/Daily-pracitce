package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class SocketConnection {

    public static final String LOCALHOST = "localhost";

    private final PrintWriter out;
    private final BufferedReader in;

    /**
     *
     * @param hostName server hostname
     * @param portNumber This is a remote port number—the number of a port on the server computer
     * @throws IOException
     */
    public SocketConnection(String hostName, int portNumber) throws IOException {
        Socket echoSocket = new Socket(hostName, portNumber);
        out = new PrintWriter(echoSocket.getOutputStream(), true);
        in = new BufferedReader(
               new InputStreamReader(echoSocket.getInputStream()));
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
}
