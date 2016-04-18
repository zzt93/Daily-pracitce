package thread.old.pv;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 7/3/15.
 *
 * Description: A emulation of sleepy barber using semaphore
 *
 * The behaviour of barber:
 *      1. no customer -- suspend itself
 *
 * The behaviour of customer:
 *      1. if barber is sleeping -- wake him up
 *      2. if more seats -- waiting
 *      3. if no more seats -- leaving
 *
 * The resources:
 *      1. barber -- not used here
 *      2. customer
 *      3. seat
 *
 */
public class Sleepy {
    public static final int NUM_SEATS = 10;
//    private Semaphore barber = new Semaphore(1);
    private Semaphore customer = new Semaphore(0);
    private int cusCount = 0;
    private Semaphore count = new Semaphore(1);


    public static void main(String[] args) {
        new Sleepy().test();
    }

    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(this::barber);
        executorService.execute(this::customers);
    }

    /**
     * The source of customers
     */
    private void customers() {
        while (true) {
            try {
                Thread.sleep(new Random(2000).nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("customer coming");
            count.P();
            if (cusCount >= NUM_SEATS) {
                count.V();
                System.out.println("customer leaving");
                continue;
            }
            cusCount++;
            count.V();
            customer.V();
        }
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
        }
    }
}
