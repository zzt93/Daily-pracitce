package net.rmiImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class RMIRegistryTest {

    private RMIRegistry localhost;

    @Before
    public void setUp() throws Exception {
        localhost = RMIRegistry.getInstance("localhost");
        localhost.register("test", new InterfaceImpl());
    }

    @Test
    public void getService() throws Exception {
        RMIRegistry.getInstance("localhost").getService("test");
    }

}