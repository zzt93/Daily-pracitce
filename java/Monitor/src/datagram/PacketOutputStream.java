package datagram;

import java.io.IOException;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class PacketOutputStream extends Writer {
    private DatagramSocket socket;
    private SocketAddress address;

    public PacketOutputStream(DatagramSocket datagramSocket, SocketAddress addr) {
        socket = datagramSocket;
        address = addr;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        byte buf[] = new byte[len];
        for (int i = 0; i < len; i++) {
            buf[i] = (byte) cbuf[i];
        }
        DatagramPacket packet = new DatagramPacket(buf, len, address);
        socket.send(packet);
        System.out.println("send");
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}
