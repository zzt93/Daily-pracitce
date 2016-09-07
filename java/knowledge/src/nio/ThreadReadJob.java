package nio;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class ThreadReadJob implements Runnable{

    private final int order;
    private final Scanner scanner;

    public ThreadReadJob(int order) {
        this.order = order;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            System.out.println("Thread " + order + ":" + scanner.next());
        }
    }
}
