package connection;

import datagram.P2PDatagram;
import socketWay.client.SocketClientConnection;
import socketWay.server.SocketServer;
import urlWay.UrlConnection;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class ConnectionFactory {

    public static SocketClientConnection getSocketClientConnection() throws IOException {
        return new SocketClientConnection(WebConnection.LOCALHOST, WebConnection.SERVER_LISTENED_PORT);
    }

    public static SocketServer getSocketServer() throws IOException {
        return new SocketServer(SocketServer.LISTEN_PORT);
    }

    public static UrlConnection getServerUrl() throws IOException {
        return new UrlConnection("http://" + WebConnection.LOCALHOST + ":" + WebConnection.SERVER_LISTENED_PORT);
    }

    public static P2PDatagram getClientDatagram() throws SocketException, UnknownHostException {
        return new P2PDatagram(WebConnection.CLIENT_PORT, WebConnection.LOCALHOST, WebConnection.SERVER_LISTENED_PORT);
    }

    public static P2PDatagram getServerDatagram() throws SocketException, UnknownHostException {
        return new P2PDatagram(WebConnection.SERVER_LISTENED_PORT, WebConnection.LOCALHOST, WebConnection.CLIENT_PORT);
    }

}
