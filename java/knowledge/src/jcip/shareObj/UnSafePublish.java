package jcip.shareObj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/25/16.
 * <p>
 * Usage:
 */
public class UnSafePublish {
    private int n;

    public UnSafePublish(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish(42);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            unSafePublish.assertSanity();
        });
        service.shutdown();
    }
}
