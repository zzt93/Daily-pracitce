package jcip.jmm;

import annotation.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class SafeInit {
    @GuardedBy("this")
    private static PossibleReorder resource;

    public synchronized static PossibleReorder getInstance() {
        if (resource == null) {
            resource = new PossibleReorder();
        }
        return resource;
    }
}
