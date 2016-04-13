package thread.se5;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 4/13/16.
 * <p>
 * Usage:
 */
class DelayedTask implements Runnable, Delayed {
    private static AtomicInteger count = new AtomicInteger();
    private long delay;
    private long latency;
    private int id;

    public DelayedTask(long delay) {
        id = count.getAndAdd(1);
        this.delay = delay;
        latency = TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MICROSECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(latency - System.nanoTime(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long d1 = o.getDelay(TimeUnit.NANOSECONDS);
        long d2 = getDelay(TimeUnit.NANOSECONDS);
        if (d1 < d2) {
            return 1;
        } else if (d1 > d2) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return String.format("[%1$-4d]", delay) +
                " Task " + id + "\t";
    }

    @Override
    public void run() {
        System.out.print(this);
    }
}

class EndSentinel extends DelayedTask {

    private final ExecutorService service;

    public EndSentinel(long delay, ExecutorService service) {
        super(delay);
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("close all");
        service.shutdownNow();
    }
}

public class DelayedTaskDemo {
    public static void main(String[] args) {
        int size = 20;
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        Random random = new Random(47);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            queue.add(new DelayedTask(random.nextInt(5000)));
        }
        queue.add(new EndSentinel(500, service));
        service.execute(() -> {
            while (!Thread.interrupted()) {
                try {
                    DelayedTask take = queue.take();
                    take.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
    }
}
