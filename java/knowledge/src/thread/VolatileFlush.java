package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/2/16.
 * <p></p>
 * Usage:
 * <p>
 */
public class VolatileFlush implements Runnable {
    private int a;
    private volatile int b;

    @Override
    public void run() {
        a = 5;
        b = 6;
    }

    /**
     * All Possible Result:
     * <li>0 0</li>
     * <li>5 0</li>
     * <li>5 6</li>
     * <li>0 6</li>
     * <p>
     * 0 6 is possible for a is not volatile and may be in the cache and not in the memory
     */
    public void showAThenB() {
        System.out.println(a + " " + b);
    }

    /**
     * All Possible Result:
     * <ul>
     * <li>0 0</li>
     * <ul> two cases
     *     <li>setting a and b is not run</li>
     *     <li>setting b is not run, and a is not flushed</li>
     * </ul>
     * <li>6 5</li>
     * <li>0 5</li>
     * </ul>
     * <p>
     * 6 0 is impossible for preventing the reorder and memory visibility
     * <a href="http://stackoverflow.com/questions/17108541/
     * happens-before-relationships-with-volatile-fields-and-synchronized-blocks-in-jav?rq=1">this answer</a>
     * and <a href="http://stackoverflow.com/questions/9187527/volatile-why-prevent-compiler-reorder-code">here</a>
     */
    public void showBThenA() {
        System.out.println(b + " " + a);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        VolatileFlush command = new VolatileFlush();
        service.execute(command);
        for (int i = 0; i < 10000; i++) {
            command = new VolatileFlush();
            service.execute(command);
            warmUp(1);
            command.showAThenB();
        }
        System.out.println("---------------------");
        for (int i = 0; i < 10000; i++) {
            command = new VolatileFlush();
            service.execute(command);
            warmUp(1);
            command.showBThenA();
        }
        service.shutdown();
    }

    private static void warmUp(int i) {
    }
}
