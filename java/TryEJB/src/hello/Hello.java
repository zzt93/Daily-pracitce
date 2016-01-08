package hello;

import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage:
 */
@Remote
public interface Hello {
    String sayHello(String name);

    @Remove
    void remove();
}
