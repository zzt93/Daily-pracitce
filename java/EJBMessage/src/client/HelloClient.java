package client;

import remote.Hello;

/**
 * Created by zzt on 12/31/15.
 * <p>
 * Usage:
 */
public class HelloClient {
    public static void main(String[] args) {
        Hello hello = (Hello) JNDIFactory.getResource("ejb:/learnEJB_ejb//HelloEJB!remote.Hello");
        System.out.println(hello.sayHello("tom"));
    }
}
