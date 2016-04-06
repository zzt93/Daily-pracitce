package thread.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 */
public class AtomicPrimitive implements Runnable{

    private AtomicInteger integer = new AtomicInteger(0);

    public int getVal() {
        return integer.get();
    }

    public void inc() {
        integer.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            inc();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Aborting");
            }
        }, 5000);

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicPrimitive ait = new AtomicPrimitive();
        exec.execute(ait);
        while(true) {
            int val = ait.getVal();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

}
