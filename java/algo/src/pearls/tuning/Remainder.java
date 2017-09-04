package pearls.tuning;

/**
 * Created by zzt on 5/31/16.
 * <p>
 * <h3></h3>
 */
public class Remainder {

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        int res = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                res += remainder(i + 1, j + 1);
            }
        }
        if (res == System.nanoTime()) {
            System.out.print(' ');
        }
        System.out.println(System.nanoTime() - start);
        Thread.sleep(100);
        start = System.nanoTime();
        res = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                res += remainder2(i + 1, j + 1);
            }
        }
        if (res == System.nanoTime()) {
            System.out.print(' ');
        }
        System.out.println(System.nanoTime() - start);
    }

    private static int remainder(int i, int j) {
        return i % j;
    }

    private static int remainder2(int i, int j) {
        while (i > j) {
            i -= j;
        }
        return i;
    }


}
