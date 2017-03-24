package jcip.synchronizer;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by zzt on 5/18/16.
 * <p>
 * <h3>Des</h3>
 * <p>
 * OneShotLatch could have been implemented by extending AQS rather than
 * delegating to it, but this is undesirable for several reasons [EJ Item 14]. Doing so
 * would undermine the simple (two-method) interface of OneShotLatch , and while
 * the public methods of AQS won’t allow callers to corrupt the latch state, callers
 * could easily use them incorrectly. None of the synchronizers in java.util.con-
 * current extends AQS directly—they all delegate to private inner subclasses of
 * AQS instead.
 * </p>
 */
public class OneShotLatch {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void await() throws InterruptedException {
        sync.acquireInterruptibly(0);
    }

    public void signal() {
        sync.release(0);
    }
}
