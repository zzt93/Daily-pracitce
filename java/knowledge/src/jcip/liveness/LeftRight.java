package jcip.liveness;

/**
 * Created by zzt on 5/7/16.
 * <p>
 * Usage:
 */
public class LeftRight {
    private final Object left = new Object();
    private final Object right = new Object();

    public void left() {
        synchronized (left) {
            right();
        }
    }

    private void right() {
        synchronized (right) {
            left();
        }
    }
}
