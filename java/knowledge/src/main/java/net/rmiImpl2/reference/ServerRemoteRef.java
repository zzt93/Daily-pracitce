package net.rmiImpl2.reference;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 5/4/17.
 * <p>
 * <h3></h3>
 */
public class ServerRemoteRef {

    private RemoteRef client;
    private Set<Lease> holders = new HashSet<>();

    public ServerRemoteRef(RemoteRef ref) {
        this.client = ref;
    }

    public void remove(Lease lease) {
        holders.remove(lease);
        if (holders.isEmpty()) {
            RemoteTable.remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerRemoteRef that = (ServerRemoteRef) o;

        return client.equals(that.client);
    }

    @Override
    public int hashCode() {
        return client.hashCode();
    }
}
