package thread.old;

/**
 * Created by zzt on 2/26/15.
 */
public class WaitTrail {

    int count = 0;
    static boolean visited = false;

    class Counter implements Runnable {
        Object monitor;

        int aim;

        public Counter(Object monitor, int aim) {
            this.monitor = monitor;
            this.aim = aim;
        }

        @Override
        public void run() {
            SimpleThreads.threadMessage("in run " + aim);
            synchronized (monitor) {
                while (count < 10) {
                    if (count % 2 == aim) {
                        try {
                            visited = true;
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
//                        if (visited) {
                        System.out.println(count++);
                        monitor.notify();
//                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        WaitTrail trail = new WaitTrail();
        Counter odd = trail.new Counter(o, 1);
        Counter even = trail.new Counter(o, 0);
        Thread t1 = new Thread(odd);
        Thread t2 = new Thread(even);
        t1.start();
        t2.start();
    }
}
