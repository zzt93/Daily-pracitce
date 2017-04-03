package net.rmiImpl;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.rmi.RemoteException;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class RemoteService {
    private static final int SERVICE_NAME_MAX_LEN = 1000;
    public static final int INTERFACE_MAX_SIZE = 1 << 12;
    private final NetworkHelper networkHelper;

    public RemoteService(InetSocketAddress remoteAddr) throws RemoteException {
        try {
            networkHelper = new NetworkHelper(remoteAddr);
        } catch (SocketException e) {
            throw new RemoteException("Invalid: ", e);
        }
    }

    public Object getService(String serviceName) throws IOException, ClassNotFoundException {
        byte[] data = fromObjToBytes(serviceName);
        networkHelper.send(data);
        byte[] tmp = networkHelper.receive(INTERFACE_MAX_SIZE);
        return fromBytesToObj(tmp);
    }

    private Object fromBytesToObj(byte[] tmp) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(tmp));
        return inputStream.readObject();
    }

    private byte[] fromObjToBytes(String serviceName) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(SERVICE_NAME_MAX_LEN);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(serviceName);
        return out.toByteArray();
    }
}
