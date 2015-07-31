package thread;

/**
 * Created by zzt on 7/30/15.
 * <p>
 * Description:
 * The following code cause 'java.lang.IllegalMonitorStateException'
 * A single lock?
 *
 * __________________
 *
 * Answer: Every time you set `mutex` to `true` or `false`,
 * it will autoboxing a new object and
 *
 */
public class Alternate {
    static Boolean mutex = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Odd(mutex));
        Thread t2 = new Thread(new Even(mutex));
        t1.start();
        t2.start();
    }
}

class Odd implements Runnable {
    Boolean mutex;

    Odd(Boolean mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            synchronized (mutex) {
                while (mutex) {
                    mutex.wait();
                }
                System.out.println("odd");
                mutex = true;
                mutex.notifyAll();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Even implements Runnable {
    Boolean mutex;

    Even(Boolean mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            synchronized (mutex) {
                while (!mutex) {
                    mutex.wait();
                }
                System.out.println("even");
                mutex = false;
                mutex.notifyAll();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}