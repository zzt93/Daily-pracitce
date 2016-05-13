package jcip.buildingBlock;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by zzt on 4/25/16.
 * <p>
 * Usage: A bounded set with <b>only one semaphore</b> to coordinate producer and consumer
 * @see jcip.test.BoundedBuffer
 */
public class BoundedSet<T> {
    private Set<T> set;
    private Semaphore semaphore;

    public BoundedSet(Set<T> set, int size) {
        this.set = Collections.synchronizedSet(set);
        semaphore = new Semaphore(size);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();
        /**
         * if context switch happen here, another thread try to
         * remove this element, it will fail to remove it.
         * But due to the following check, invariant is still hold.
         * <pre>
            if (res) {
                semaphore.release();
            }
         * </pre>
         *
         */
        boolean res = false;
        try {
            res = set.add(t);
        } finally {
            if (!res) {
                semaphore.release();
            }
        }
        return res;
    }

    public boolean remove(T o) {
        boolean res = set.remove(o);
        if (res) {
            semaphore.release();
        }
        return res;
    }
}
