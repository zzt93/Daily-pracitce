package remote;

import javax.ejb.Remote;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage:
 */
@Remote
public interface Hello {
    String sayHello(String name);
}
