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
    /**
     * this index indicates the position of reading operation
     */
    private int pointer = 0;
    /**
     * this index indicates the size of read bytes
     */
    private int size = 0;
    private boolean lastIsReceived = false;


    public PacketInputStream(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    /**
     * Have to return -1 for this inputStream to work properly
     *
     * @return size of byte read
     *
     * @throws IOException
     * @see InputStream#read(byte[], int, int)
     */
    @Override
    public int read() throws IOException {
        while (pointer >= size) {
            if (!lastIsReceived) {
                tryReceivePacket();
            } else {
                lastIsReceived = false;
                return -1;
            }
        }
        return buf[pointer++];
    }

    private void tryReceivePacket() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, PACKET_SIZE);
        datagramSocket.receive(packet);
        System.out.println("received");
        // re-init buf statistic
        size = packet.getLength();
        pointer = 0;
        lastIsReceived = true;
    }

    @Override
    public int available() throws IOException {
        if (!lastIsReceived) {
            datagramSocket.setSoTimeout(50);
            tryReceivePacket();
        }
        return size > pointer ? size - pointer : 0;
    }
}
