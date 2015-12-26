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

    public P2PDatagram(int herePort, String remoteName, int remotePort)
            throws SocketException, UnknownHostException {
        // Constructs a datagram socket and binds it to the specified port on the local host machine.
        datagramSocket = new DatagramSocket(herePort);
        InetAddress addr = InetAddress.getByName(remoteName);
        address = new InetSocketAddress(remotePort);
    }

    @Override
    public PrintWriter getOut() {
        return new PrintWriter(new PacketOutputStream(datagramSocket, address));
    }

    @Override
    public BufferedReader getIn() {
        return new BufferedReader(new InputStreamReader(new PacketInputStream(datagramSocket)));
    }

    @Override
    public void close() throws IOException {
        datagramSocket.close();
    }
}
