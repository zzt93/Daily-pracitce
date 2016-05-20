package jcip.atomic;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */
public class LockPseudoRandom extends PseudoRandom {

    private Lock lock = new ReentrantLock();
    private int seed;

    public LockPseudoRandom(int seed) {
        this.seed = seed;
    }


    @Override
    public int nextInt(int bound) {
        lock.lock();
        try {
            final int next = calculateNext(seed);
            final int res = next % bound;
            return res > 0 ? res : bound + res;
        } finally {
            lock.unlock();
        }
    }
}
