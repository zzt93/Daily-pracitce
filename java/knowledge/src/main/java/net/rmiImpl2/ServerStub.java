package net.rmiImpl2;

import net.rmiImpl.RemoteObj;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class ServerStub {

    private int port;
    private RemoteObj obj;

    public ServerStub(RemoteObj obj) {
        this(9000, obj);
    }

    public ServerStub(int port, RemoteObj obj) {
        this.port = port;
        this.obj = obj;
    }

}
