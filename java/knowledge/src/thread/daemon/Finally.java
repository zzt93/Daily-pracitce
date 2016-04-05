package thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 * Daemons are terminated "abruptly" when the last of the non-daemons terminates.
 * So as soon as main( ) exits, the JVM shuts down all the daemons immediately, without any
 * of the formalities you might have come to expect.
 */
class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("This should always run?");
        }
    }
}

public class Finally {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
