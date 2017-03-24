package jcip.atomic;

import annotation.NotThreadSafe;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */
@NotThreadSafe
public abstract class PseudoRandom {

    private static final int multiplier = 123;
    private static final int addend = 376;

    int calculateNext(int s) {
        return multiplier * s + addend;
    }

    public abstract int nextInt(int bound);
}
