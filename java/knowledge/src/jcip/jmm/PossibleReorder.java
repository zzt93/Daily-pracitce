package jcip.jmm;

/**
 * Created by zzt on 5/21/16.
 * <p>
 * <h3></h3>
 */
public class PossibleReorder {

    private static int x, y;
    private static int a, b;

    public static void main(String[] args) throws InterruptedException {

        Thread f = new Thread(() -> {
            x = 1;
            a = y;
        });

        Thread s = new Thread(() -> {
            y = 1;
            b = x;
        });
        f.start();
        s.start();
        f.join();
        s.join();
        System.out.println("(" + a + ", " + b + ")");
    }
}
