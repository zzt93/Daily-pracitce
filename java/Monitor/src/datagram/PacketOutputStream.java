package datagram;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class PacketOutputStream extends OutputStream {
    public static final int MAX = 256;
    private DatagramSocket socket;
    private SocketAddress address;
    private byte[] buf = new byte[MAX];
    private int count;

    public PacketOutputStream(DatagramSocket datagramSocket, SocketAddress addr) {
        socket = datagramSocket;
        address = addr;
    }

//    @Override
//    public void write(char[] cbuf, int off, int len) throws IOException {
//        if (count + len >= MAX) {
//            flush();
//        }
//        do {
//            int i;
//            for (i = 0; count + i < MAX && i < len; i++) {
//                buf[count + i] = (byte) cbuf[i];
//            }
//            count += i;
//            len -= i;
//            if (count == MAX) {
//                flush();
//            }
//        } while (len > 0);
//    }

    @Override
    public void write(int b) throws IOException {
        buf[count++] = (byte) b;
        if (count == MAX) {
            flush();
        }
    }

    @Override
    public void flush() throws IOException {
        if (count == 0) {
            return;
        }
        DatagramPacket packet = new DatagramPacket(buf, count, address);
        socket.send(packet);
        count = 0;
        System.out.println("send");
    }
//
//    @Override
//    public void close() throws IOException {
//    }
}
