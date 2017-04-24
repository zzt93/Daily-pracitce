package net.rmiImpl2.reference;

import java.net.InetSocketAddress;

/**
 * Created by zzt on 4/16/17.
 * <p>
 * <h3></h3>
 */
public class Lease {

    private InetSocketAddress holder;
    private long lease;

    private RemoteRef ref;

    public void decrease() {
        lease--;
        if (lease == 0) {
            ref.remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lease lease = (Lease) o;

        return holder != null ? holder.equals(lease.holder) : lease.holder == null;
    }

    @Override
    public int hashCode() {
        return holder != null ? holder.hashCode() : 0;
    }
}
