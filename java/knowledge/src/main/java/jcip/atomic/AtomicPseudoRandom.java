package jcip.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */
public class AtomicPseudoRandom extends PseudoRandom {

    private AtomicInteger seed;

    public AtomicPseudoRandom(int s) {
        seed = new AtomicInteger(s);
    }

    public int nextInt(int bound) {
        while (true) {
            final int s = seed.get();
            final int next = calculateNext(s);
            if (seed.compareAndSet(s, next)) {
                int res = next % bound;
                return res > 0 ? res : res + bound;
            }
        }
    }
}
