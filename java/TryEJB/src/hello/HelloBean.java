package hello;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Created by zzt on 12/30/15.
 * <p>
 * J2EE is a platform for distributed applications
 * which provides a set of services arround transaction management, security checks, state
 * management, and resource management. It uses JSPs, Servlets, EJBs, JDBC, JMS,...
 * The EJBs are the part of J2EE which are transaction orientated. They make a J2EE application scalable.
 * <p>
 * If concurrent access to a stateless bean is attempted, the container simply routes each request to a different
 * instance. This makes a stateless session bean automatically thread-safe
 * <p></p>
 * <a href="http://stackoverflow.com/questions/1935178/correct-usage-of-stateful-beans-with-servlets/1935476#1935476">
 *     concurrent access to SFSB</a>
 */
@Stateless(name = "HelloEJB")
@LocalBean
@Interceptors({HelloCallBack.class})
public class HelloBean implements Hello {
    /*
    A persistence context is a set of managed entity instances that exist in a particular data store.
    The EntityManager interface defines the methods that are used to interact with the persistence context.
    This manager create/remove/find a entity from context

    error:
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
