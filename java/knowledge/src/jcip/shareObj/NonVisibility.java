package jcip.shareObj;

/**
 * Created by zzt on 4/17/16.
 * <p>
 * Usage:
 */
public class NonVisibility {

    private static int number;
    private static boolean ready;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!ready) {
                System.out.println(".");
                Thread.yield();
            }
            System.out.println(number);
        }).start();
        // wait some time for thread creation
        System.out.println("Thread start");
        number = 442;
        ready = true;
    }
}
