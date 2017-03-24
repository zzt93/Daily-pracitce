package net.client;

import net.common.Compute;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by zzt on 12/27/15.
 * <p>
 * Usage:
 */
public class ComputePI {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String name = "compute";
        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            Pi task = new Pi(Integer.parseInt(args[1]));
            BigDecimal pi = comp.compute(task);
            System.out.println(pi);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
