package net.rmiImpl2.reference;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzt on 4/11/17.
 * <p>
 * <h3></h3>
 */
public class RemoteRef {
    private InetAddress address;
    private int port;

    private Set<Lease> holders = new HashSet<>();

    public void remove(Lease lease) {
        holders.remove(lease);
        if (holders.isEmpty()) {
            RemoteTable.remove(this);
        }
    }
}
