package learnRMI.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage:
 */
public interface Notify extends Remote{

    void notify(String message) throws RemoteException;
}
