package jcip.atomic;

import annotation.Immutable;
import annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zzt on 5/20/16.
 * <p>
 * <h3></h3>
 */


@ThreadSafe
public class CasNumberRange {
    @Immutable
    private class NumberRange {

        private final int upper;
        private final int lower;

        NumberRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }

    private AtomicReference<NumberRange> range = new AtomicReference<>();

    public int upper() {
        return range.get().upper;
    }

    public int lower() {
        return range.get().lower;
    }

    public void setLower(int i) {
        while (true) {
            final NumberRange old = this.range.get();
            if (i > old.upper) {
                throw new IllegalArgumentException();
            }
            final NumberRange newR = new NumberRange(old.upper, i);
            if (range.compareAndSet(old, newR)) {
                return;
            }
        }
    }

}
