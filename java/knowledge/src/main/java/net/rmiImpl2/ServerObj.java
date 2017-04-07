package net.rmiImpl2;

import net.rmiImpl.RemoteObj;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class ServerObj {

    private int port;
    private RemoteObj obj;

    public ServerObj(RemoteObj obj) {
        this(9000, obj);
    }

    public ServerObj(int port, RemoteObj obj) {
        this.port = port;
        this.obj = obj;
    }

}
