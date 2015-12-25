package client;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class Tester {
    ExecutorService executor = Executors.newCachedThreadPool();

    public Tester() {
        executor.execute(new Ping());
    }

    public static void main(String[] args) {
        new Tester();
    }
}
