package hello;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage: If concurrent access to a single bean is attempted, the container simply routes each request to a different
 * instance. This makes a stateless session bean automatically thread-safe
 */
@Stateless(name = "HelloEJB")
@LocalBean
@Interceptors({HelloCallBack.class})
public class HelloBean implements Hello {
    /*
    A persistence context is a set of managed entity instances that exist in a particular data store.
    The EntityManager interface defines the methods that are used to interact with the persistence context.
    This manager create/remove/find a entity from context

    this cause error:
    WFLYJPA0033: Can't find a persistence unit named null in deployment \"TryEJB.jar\""},
    W(ild)FLYJ(ava)P(ersistent)A(PI)
    which means that I didn't add persistence.xml
     */
    @PersistenceContext
    EntityManager entityManager;

    public HelloBean() {
    }

    @Override
    public String sayHello(String name) {
        return "hello world! " + name;
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("helloBean removed");
    }
}
