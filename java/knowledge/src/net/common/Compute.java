package net.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zzt on 12/27/15.
 * <p>
 * Usage:
 */
public interface Compute extends Remote {

    <T> T compute(Task<T> t) throws RemoteException;
}
