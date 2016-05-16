package jcip.explicitLock;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3></h3>
 */
public class ReadWriteMap<K, V> {
    private final Map<K, V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public ReadWriteMap(Map<K, V> map) {
        this.map = map;
    }

    public V put(K k, V v) {
        w.lock();
        try {
            return map.put(k, v);
        } finally {
            w.unlock();
        }
    }

    public V get(K k) {
        r.lock();
        try {
            return map.get(k);
        }finally {
            r.unlock();
        }
    }
}
