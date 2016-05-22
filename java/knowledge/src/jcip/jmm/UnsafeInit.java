package jcip.jmm;

import annotation.NotThreadSafe;
import sun.misc.Resource;

/**
 * Created by zzt on 5/22/16.
 * <p>
 * <h3></h3>
 */
@NotThreadSafe
public class UnsafeInit {

    private static PossibleReorder resource;

    public static PossibleReorder getInstance() {
        /**
         * Problem one:
         * data race of resource's read/write
         */
        if (resource == null) {
            /**
             * Problem two:
             * unsafe publication
             */
            resource = new PossibleReorder();
        }
        return resource;
    }
}
