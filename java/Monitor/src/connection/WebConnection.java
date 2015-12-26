package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public interface WebConnection {

    String LOCALHOST = "localhost";
    int SERVER_LISTENED_PORT = 10000;
    int CLIENT_PORT = 10001;

    PrintWriter getOut();

    BufferedReader getIn();

    void close() throws IOException;
}
