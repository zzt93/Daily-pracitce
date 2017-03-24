package jcip.jmm;

import annotation.NotThreadSafe;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3></h3>
 */
@NotThreadSafe
public class DoubleCheckedLocking {

    private static PossibleReorder resource;

    public static PossibleReorder getInstance() {
        /**
         * Problem one:
         * data race of resource's read/write
         * see the stale value
         */
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null) {
                    resource = new PossibleReorder();
                }
            }
        }
        return resource;
    }
}
