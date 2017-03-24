package reflect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by zzt on 4/27/16.
 * <p>
 * Usage:
 * A kind of simulation to the implementation of WeakHashMap
 */
public class UseReferenceQueue {

    private static ReferenceQueue<Integer> queue;

    public static void main(String[] args) {
        queue = new ReferenceQueue<>();
        ReferenceWithCleanUp<Integer, VeryBig> cleanUp
                = new ReferenceWithCleanUp<>(1, queue, new VeryBig("1"));
        // some time later
        cleanUp();
    }

    public static void cleanUp() {
        ReferenceWithCleanUp<? extends Integer, VeryBig> ref;
        Object obj;
        while ((obj = queue.poll()) != null) {
            ref = (ReferenceWithCleanUp<? extends Integer, VeryBig>) obj;
            ref.cleanUp();
        }
    }
}

class ReferenceWithCleanUp<T, V> extends WeakReference<T> {

    private V v;

    public ReferenceWithCleanUp(T referent, ReferenceQueue<? super T> q, V v) {
        super(referent, q);
        this.v = v;
    }

    public void cleanUp() {
        // clean up value
        this.v = null;
    }
}