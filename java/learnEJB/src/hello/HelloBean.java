package hello;

import remote.Hello;

import javax.ejb.Stateless;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage: If concurrent access to a single bean is attempted, the container simply routes each request to a different
 * instance. This makes a stateless session bean automatically thread-safe
 */
@Stateless(name = "HelloEJB")
public class HelloBean implements Hello {
    public HelloBean() {
    }

    @Override
    public String sayHello(String name) {
        return "hello world! " + name;
    }
}
