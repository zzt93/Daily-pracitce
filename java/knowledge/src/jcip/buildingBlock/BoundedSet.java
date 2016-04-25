package jcip.buildingBlock;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by zzt on 4/25/16.
 * <p>
 * Usage:
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

    public boolean remove(Object o) {
        boolean res = set.remove(o);
        if (res) {
            semaphore.release();
        }
        return res;
    }
}
