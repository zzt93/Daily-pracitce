package net.rmiImpl2.reference;

import java.rmi.Remote;
import java.util.HashMap;

/**
 * Created by zzt on 4/11/17.
 * <p>
 * <h3></h3>
 * <li>holders: set of client processes that have proxies
 * <ul>
 * <li>when a client want a remote ref, client's id will be added to holders</li>
 * <li>when a client's remote object GCed, it notify server, remove from server</li>
 * <li>when no holders, real object can be GCed</li>
 * <li>in order to deal with communication error, it may have lease</li>
 * </ul>
 * </li>
 */
public class RemoteTable {

    private static HashMap<RemoteRef, Remote> refToProxy = new HashMap<>();

    public static Remote remove(RemoteRef key) {
        return refToProxy.remove(key);
    }
}
