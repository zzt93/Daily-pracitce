package jcip.buildingBlock;

import java.util.concurrent.*;

/**
 * Created by zzt on 4/26/16.
 * <p>
 * Usage:
 */
public class Cache2<K, V> implements Computable<K, V> {
    private final ConcurrentHashMap<K, Future<V>> map = new ConcurrentHashMap<>();
    private final Computable<K, V> real;

    public Cache2(Computable<K, V> real) {
        this.real = real;
    }

    @Override
    public V compute(K k) throws InterruptedException {
        while (true) {
            Future<V> future = map.get(k);
            if (future == null) {
                Callable<V> job = () -> real.compute(k);
                FutureTask<V> ft = new FutureTask<>(job);
                future = map.putIfAbsent(k, ft);
                if (future == null) {
                    future = ft;
                    ft.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException c) {
                map.remove(k, future);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
