package reflect;

import java.lang.ref.*;
import java.util.LinkedList;


class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    protected void finalize() throws Throwable {
        System.out.println("Finalizing " + ident);
        super.finalize();
    }
}

/**
 * Created by zzt on 3/22/16.
 * <p>
 * <h3>Behaviour</h3>
 * <li>only weakly referenced object are GCed after {@link System#gc()}</li>
 * <li>phantom referenced object are just discarded and enqueued</li>
 * <h3>Question</h3>
 * <li>Why weakly referenced object not enqueued </li>
 * <li></li>
 */
public class References {
    private static ReferenceQueue<VeryBig> rq =
            new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null)
            System.out.println("In queue: " + inq.get());
    }

    public static void main(String[] args) {
        int size = 10;
        // Or, choose size via the command line:
        if (args.length > 0) {
            size = new Integer(args[0]);
        }
        LinkedList<SoftReference<VeryBig>> sa =
                new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<>(
                    new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }
        LinkedList<WeakReference<VeryBig>> wa =
                new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<>(
                    new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s =
                new SoftReference<>(new VeryBig("Single Soft"));
        WeakReference<VeryBig> w =
                new WeakReference<>(new VeryBig("Single Weak"));
        // try to start gc to reclaim memory: weak reference will be reclaimed
        checkQueue();
        System.gc();
        checkQueue();

        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<>(
                    new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}
