package jcip.shareObj;

import annotation.Immutable;
import annotation.ThreadSafe;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by zzt on 4/27/16.
 * <p>
 * Usage:
 * <li>two level visibility: the object's reference is visible to all thread
 * , the state of this object is also visible</li>
 * <li>Proof for final make two level: the following program</li>
 * <li>volatile and lock also make it</li>
 */
@ThreadSafe
public class SafePublish {

    /**
     * volatile: When a thread sets the volatile cache field to
     * reference a new OneValueCache, the new cached data becomes immediately visible to
     * other threads.
     */
    private volatile OneValueCache cache =
            new OneValueCache(null, null);

    public void service(Long in, StringBuilder resp) {
        BigInteger i = BigInteger.valueOf(in);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        resp.append(Arrays.toString(factors));
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }
}

@Immutable
class OneValueCache {
    // replace final with volatile is also ok!
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}