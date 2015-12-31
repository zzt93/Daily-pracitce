package client;

import remote.Hello;

/**
 * Created by zzt on 12/31/15.
 * <p>
 * Usage:
 */
public class HelloClient {
    public static void main(String[] args) {
        Hello hello = (Hello) EJBFactory.getEJB("ejb:/learnEJB//HelloBean!remote.Hello");
        System.out.println(hello.sayHello("tom"));
    }
}
