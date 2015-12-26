package datagram;

import connection.WebConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class DatagramServer implements WebConnection {

    public DatagramServer() {
    }

    @Override
    public PrintWriter getOut() {
        return null;
    }

    @Override
    public BufferedReader getIn() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
