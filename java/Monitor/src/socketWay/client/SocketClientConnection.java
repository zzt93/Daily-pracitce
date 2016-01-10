package socketWay.client;

import connection.WebConnection;

import java.io.*;
import java.net.Socket;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class SocketClientConnection implements WebConnection {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Socket client;

    /**
     * @param hostName   server hostname
     * @param portNumber This is a remote port number—the number of a port on the server computer
     *
     * @throws IOException
     */
    public SocketClientConnection(String hostName, int portNumber) throws IOException {
        client = new Socket(hostName, portNumber);
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "utf8"), true);
        in = new BufferedReader(
                new InputStreamReader(client.getInputStream(), "utf8"));
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
        client.close();
    }
}
