package net.rmiImpl;

import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class RMIRegistryTest {

    private RMIRegistry localhost;

    @Before
    public void setUp() throws Exception {
        localhost = RMIRegistry.getServer();
        localhost.register("test", new InterfaceImpl());
    }

    @Test
    public void getService() throws Exception {
        AInterface service = (AInterface) RMIRegistry.getClient("localhost").getService("test");
        service.f();
        service.str(12);
    }

}