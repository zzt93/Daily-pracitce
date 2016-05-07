package jcip.liveness;

/**
 * Created by zzt on 5/7/16.
 * <p>
 * Usage:
 */
public class TransferMoney {

    private static final Object tieLock = new Object();

    public static void transferMoney(final Account in,
                                     final Account out,
                                     final Dollar amount) {
        final int inHash = System.identityHashCode(in);
        final int outHash = System.identityHashCode(out);
        if (inHash < outHash) {
            synchronized (in) {
                synchronized (out) {

                }
            }
        } else if (inHash > outHash) {
            synchronized (out) {
                synchronized (in) {

                }
            }
        } else {
            /**
             * By acquiring the tie-breaking lock before acquiring either
             Account lock, we ensure that only one thread at a time performs the risky task of
             acquiring two locks in an arbitrary order, eliminating the possibility of deadlock
             (so long as this mechanism is used consistently). If hash collisions were common,
             this technique might become a concurrency bottleneck (just as having a single,
             program-wide lock would), but because hash collisions with System.identity-
             HashCode are vanishingly infrequent, this technique provides that last bit of safety
             at little cost.
             */
            synchronized (tieLock) {
                synchronized (out) {
                    synchronized (in) {

                    }
                }
            }
        }
    }

    private static class Account {
    }

    private static class Dollar {
    }
}
