package client;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 12/24/15.
 * <p>
 * Usage:
 */
public class Tester {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public Tester() {
        executor.execute(new Ping());
    }

    public static void main(String[] args) {
        new Tester();
    }
}
