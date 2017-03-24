package jcip.composeObj;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by zzt on 4/20/16.
 * <p>
 * Usage:
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy("this")
    private int x, y;

    /**
     * The private constructor exists to avoid the race condition
     * that would occur if the copy constructor
     * were implemented as this(p.x, p.y) ;
     * this is an example of the private constructor capture idiom
     *
     * @param a array
     */
    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * safe way to publish x, y at the same time, otherwise the invariant of (x, y)
     * may inconsistent when implemented by two separated getter
     *
     * @return point array
     */
    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
