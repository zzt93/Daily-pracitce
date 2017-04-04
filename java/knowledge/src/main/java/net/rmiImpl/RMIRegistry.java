package net.rmiImpl;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class RMIRegistry {

    private static final int port = 9000;
    private final RemoteService remoteService;
    private HashMap<String, Object> localServices = new HashMap<>();

    private RMIRegistry(String hostName, boolean server) throws RemoteException {
        InetSocketAddress remoteAddr = new InetSocketAddress(hostName, port);
        remoteService = new RemoteService(remoteAddr, server);
    }

    public static RMIRegistry getClient(String serverHostName) throws RemoteException {
        return new RMIRegistry(serverHostName, false);
    }

    public static RMIRegistry getServer() throws RemoteException {
        return new RMIRegistry("localhost", true);
    }


    public <T extends Serializable> void register(String name, T aInterface) {
        localServices.put(name, aInterface);
        startListen();
    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private volatile boolean listening = true;

    private void startListen() {
        executorService.submit(
                () -> {
                    while (listening) {
                        try {
                            Request request = remoteService.getRequest();
                            System.out.println(request);
                            remoteService.response(localServices.get(request.getRequest()), request);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
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
