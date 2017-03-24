package jcip.buildingBlock;

import java.util.concurrent.*;

/**
 * Created by zzt on 4/26/16.
 * <p>
 * Usage:
 * <li>
 * Use proxy pattern
 * </li>
 * <li>User concurrentHashMap to increase scalability</li>
 * <li>User Future to reduce the possibility of computing same value again</li>
 */
interface Computable<K, V> {
    V compute(K k) throws InterruptedException;
}

public class Cache<K, V> implements Computable<K, V> {
    private final ConcurrentHashMap<K, Future<V>> map = new ConcurrentHashMap<>();
    private final Computable<K, V> real;

    public Cache(Computable<K, V> real) {
        this.real = real;
    }

    @Override
    public V compute(K k) throws InterruptedException {
        Future<V> future = map.get(k);
        if (future == null) {
            Callable<V> job = () -> real.compute(k);
            FutureTask<V> ft = new FutureTask<>(job);
            future = ft;
            // still possible to put twice
            map.put(k, ft);
            ft.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw launderThrowable(e);
        }
    }

    /**
     * If the Throwable is an Error, throw it; if it is a
     * RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }
}
