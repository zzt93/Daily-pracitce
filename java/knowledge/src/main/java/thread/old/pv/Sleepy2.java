package thread.old.pv;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 7/4/15.
 * <p>
 * Description: A emulation of sleepy barber using semaphore
 * <p>
 * The behaviour of barber:
 * 1. no customer -- suspend itself
 * <p>
 * The behaviour of customer:
 * 1. if barber is sleeping -- wake him up
 * 2. if more seats -- waiting
 * 3. if no more seats -- leaving
 * <p>
 * The resources:
 * 1. barber -- to suspend customer
 * 2. customer -- to suspend barber
 * 3. seat
 */
public class Sleepy2 {
    public static final int NUM_SEATS = 10;
    private MySemaphore barber = new MySemaphore(0);
    private MySemaphore customer = new MySemaphore(0);
    private int cusCount = 0;
    private MySemaphore count = new MySemaphore(1);


    public static void main(String[] args) {
        new Sleepy2().test();
    }

    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(11);
        executorService.execute(this::barber);
        while (true) {
            try {
                Thread.sleep(new Random(2000).nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(this::customer_i);
        }
    }

    /**
     * The behaviour of every single customer
     */
    private void customer_i() {
        System.out.println("customer coming");
        count.P();
        if (cusCount >= NUM_SEATS) {
            count.V();
            System.out.println("customer leaving");
            return;
        }
        cusCount++;
        count.V();
        customer.V();
        barber.P();
        System.out.println("get hair cut");
    }

    private void barber() {
        while (true) {
            customer.P();
            // hair-cutting
            System.out.println("waked up");
            System.out.println("cutting");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.P();
            if (cusCount <= 0) {
                count.V();
                System.out.println("no customer");
                continue;
            }
            cusCount--;
            count.V();
            barber.V();
        }
    }
}
