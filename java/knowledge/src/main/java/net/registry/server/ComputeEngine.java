package net.registry.server;

import net.registry.common.Compute;
import net.registry.common.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by zzt on 12/27/15.
 * <p>
 * Usage:
 */
public class ComputeEngine implements Compute {

    public ComputeEngine() {
    }

    @Override
    public <T> T compute(Task<T> t) throws RemoteException {
        return t.execute();
    }

    /**
     * Create and install a security manager Create and export one or more remote objects Register at least one remote
     * object with the RMI registry (or with another naming service, such as a service accessible through the Java
     * Naming and Directory Interface) for bootstrapping purposes
     *
     * @param args
     */
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Compute engine = new ComputeEngine();
            Compute stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0);
            // These methods create the remote reference object
            // containing the specified network address
            // without performing any remote communication.
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
