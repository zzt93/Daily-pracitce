package thread;

/**
 * Created by zzt on 2/26/15.
 */
public class Join {

    class Sum implements Runnable {

        int sum = 0;
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
        }
    }

    public static void main(String[] args) {
        Sum test = new Join().new Sum();
        Thread sum = new Thread(test);
        sum.start();
        try {
            sum.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(test.sum);
    }
}
