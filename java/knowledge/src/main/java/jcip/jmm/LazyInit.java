package jcip.jmm;

import annotation.ThreadSafe;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class LazyInit {

    private static class LazyLoader {
        private static PossibleReorder reorder = new PossibleReorder();
    }

    public PossibleReorder getInstance() {
        return LazyLoader.reorder;
    }
}
