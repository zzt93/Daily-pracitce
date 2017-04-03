package net;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 17/3/27.
 */
public class Multicast {

    public void sendRcv(String args[]) {
        // args give message contents & destination multicast group (e.g. "228.5.6.7")
        MulticastSocket s = null;
        try {
            InetAddress group = InetAddress.getByName(args[1]);
            s = new MulticastSocket(6789);
            s.joinGroup(group);
            byte[] m = args[0].getBytes();
            DatagramPacket messageOut =
                    new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);
            byte[] buffer = new byte[1000];
            for (int i = 0; i < 3; i++) { // get messages from others in group
                DatagramPacket messageIn =
                        new DatagramPacket(buffer, buffer.length);
                s.setSoTimeout((int) TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS));
                try {
                    s.receive(messageIn);
                } catch (SocketTimeoutException e) {
                    System.err.println("Fail to receive from a peer: " + i);
                    continue;
                }
                System.out.println("Received: " + new String(Arrays.copyOf(messageIn.getData(), messageIn.getLength())));
            }
            s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) s.close();
        }
    }

    public static void main(String[] args) {
        Multicast multicast = new Multicast();
        multicast.sendRcv(new String[]{"message content", "224.0.0.7"});
    }
}
