package datagram;

import connection.WebConnection;

import java.io.*;
import java.net.*;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class P2PDatagram implements WebConnection {

    private final DatagramSocket datagramSocket;
    private SocketAddress address;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public P2PDatagram(int herePort, String remoteName, int remotePort)
            throws SocketException, UnknownHostException {
        // Constructs a datagram socket and binds it to the specified port on the local host machine.
        datagramSocket = new DatagramSocket(herePort);
        InetAddress addr = InetAddress.getByName(remoteName);
        address = new InetSocketAddress(addr, remotePort);
        printWriter = new PrintWriter(new PacketOutputStream(datagramSocket, address), true);
        bufferedReader = new BufferedReader(new InputStreamReader(new PacketInputStream(datagramSocket)));
    }

    @Override
    public PrintWriter getOut() {
        return printWriter;
    }

    @Override
    public BufferedReader getIn() {
        return bufferedReader;
    }

    @Override
    public void close() throws IOException {
        datagramSocket.close();
    }
}
