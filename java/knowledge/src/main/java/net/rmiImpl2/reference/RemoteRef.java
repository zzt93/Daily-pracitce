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
    private String className;
    private String objHash;

    public RemoteRef(InetAddress address, int port, String className, String objHash) {
        this.address = address;
        this.port = port;
        this.className = className;
        this.objHash = objHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteRef remoteRef = (RemoteRef) o;

        if (port != remoteRef.port) return false;
        if (!address.equals(remoteRef.address)) return false;
        if (!className.equals(remoteRef.className)) return false;
        return objHash.equals(remoteRef.objHash);
    }

    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + port;
        result = 31 * result + className.hashCode();
        result = 31 * result + objHash.hashCode();
        return result;
    }
}
