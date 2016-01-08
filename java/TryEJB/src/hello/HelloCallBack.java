package hello;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.interceptor.InvocationContext;
import java.lang.reflect.InvocationHandler;

/**
 * Created by zzt on 1/4/16.
 * <p>
 * Usage:
 */
public class HelloCallBack {
    @PostConstruct
    public void construct(InvocationContext ctx){
        System.out.println("cb:construct()");
    }

    @PostActivate
    public void activate(InvocationContext ctx){
        System.out.println("cb:activate()");
    }

    @PreDestroy
    public void destroy(InvocationContext ctx) {
        System.out.println("callback destroy");
    }

}
