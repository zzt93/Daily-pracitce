package jcip.jmm;

import annotation.ThreadSafe;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class EagerInit {
    private static PossibleReorder resource = new PossibleReorder();

    public static PossibleReorder getInstance() {
        return resource;
    }
}
