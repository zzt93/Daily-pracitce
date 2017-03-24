package thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 */
public class Daemon implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Daemon());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All daemon started");
        TimeUnit.MILLISECONDS.sleep(200);
    }
}
