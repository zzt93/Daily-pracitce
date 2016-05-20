package jcip.atomic;

import annotation.ThreadSafe;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class CasCounter {

    private final SimulatedCAS cas = new SimulatedCAS();

    public CasCounter(int v) {
        cas.compareAndSet(v);
    }

    public void increment() {
        int newV;
        do {
            int old = cas.get();
            newV = old + 1;
        } while (cas.compareAndSet(newV));
    }

    public int getValue() {
        return cas.get();
    }
}
