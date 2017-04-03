package net.rmiImpl;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class RMIRegistry {

    private static final int port = 9000;
    private final RemoteService remoteService;
    private HashMap<String, Object> localServices = new HashMap<>();

    private RMIRegistry(String hostName) throws RemoteException {
        InetSocketAddress remoteAddr = new InetSocketAddress(hostName, port);
        remoteService = new RemoteService(remoteAddr);
    }

    public static RMIRegistry getInstance(String hostName) throws RemoteException {
        return new RMIRegistry(hostName);
    }

    public <T extends Serializable> void register(String name, T aInterface) {
        localServices.put(name, aInterface);
    }

    public Object getService(String name) throws IOException, ClassNotFoundException {
        if (localServices.containsKey(name)) {
            return localServices.get(name);
        }
        // check remote services
        Object service = remoteService.getService(name);
        localServices.put(name, service);
        return service;
    }
}
