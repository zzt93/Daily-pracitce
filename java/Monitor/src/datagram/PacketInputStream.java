package datagram;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public class PacketInputStream extends InputStream {
    public static final int PACKET_SIZE = 256;
    private final DatagramSocket datagramSocket;
    byte[] buf = new byte[PACKET_SIZE];
    private int pointer = 0;
    private int size = 0;


    public PacketInputStream(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public int read() throws IOException {
        while (pointer >= size) {
            DatagramPacket packet = new DatagramPacket(buf, PACKET_SIZE);
            datagramSocket.receive(packet);
            System.out.println("received");
            // re-init buf statistic
            size = packet.getLength();
            pointer = 0;
        }
        return buf[pointer++];
    }
}
