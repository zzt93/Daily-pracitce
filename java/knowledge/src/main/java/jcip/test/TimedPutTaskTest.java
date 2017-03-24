package jcip.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zzt on 5/14/16.
 * <p>
 * <h3></h3>
 */
public class TimedPutTaskTest {

    private final int trial;
    private final int pair;
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> buffer;
    private final AtomicLong putSum = new AtomicLong(0);
    private final AtomicLong getSum = new AtomicLong(0);
    private static ExecutorService service = Executors.newCachedThreadPool();
    private final BarrierTimer timer;

    public TimedPutTaskTest(int cap, int pair, int trial) {
        buffer = new BoundedBuffer<>(cap);
        timer = new BarrierTimer();
        barrier = new CyclicBarrier(2 * pair + 1, timer);
        this.trial = trial;
        this.pair = pair;
    }

    void test() {
        timer.clear();
        try {
            for (int i = 0; i < pair; i++) {
                service.execute(new Producer());
                service.execute(new Consumer());
            }
            // wait for starting
            barrier.await();
            // wait for result
            barrier.await();
            double output = timer.getTime() / (pair * trial);
            System.out.println("Throughout: " + output + "ns/item");
            assert putSum.get() == getSum.get();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int tpt = 100000;
        for (int cap = 1; cap <= 1000; cap++) {
            System.out.println("Capacity: " + cap);
            for (int pair = 1; pair <= 128; pair *= 2) {
                final TimedPutTaskTest task = new TimedPutTaskTest(cap, pair, tpt);
                System.out.print("Pairs: " + pair + "\t");
                task.test();
                System.out.print("\t");
                Thread.sleep(1000);
                task.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        service.shutdown();
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            try {
                int seed = (int) (this.hashCode() ^ System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = 0; i < trial; i++) {
                    buffer.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.addAndGet(sum);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = 0; i < trial; i++) {
                    final Integer integer = buffer.get();
                    sum += integer;
                }
                getSum.addAndGet(sum);
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
