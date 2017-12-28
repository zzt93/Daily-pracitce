package pearls;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3>Des</h3>
 * <p>suppose we define the maximum sub-vector to be the value of the largest
 * element rather than zero if all is negative, i.e. not allow empty set</p>
 */
public class MaxSumVariant {

    private static final int MIN = Integer.MIN_VALUE;

    public int maxSumVar(int[] ints) {
        int maxSoFar = MIN;
        int maxEndHere = 0;
        for (int anInt : ints) {
            maxEndHere = Math.max(anInt, maxEndHere + anInt);
            /**
             * @see methodology.dynamic.MaxOfSumOpt
             *
             * verification:
             * i == 0, maxEndHere is max sum end at 0 -- ints[0]
             * suppose i == k-1, maxEndHere is max sum end at i-1
             * when i == k, suppose the the sum([x, i]) larger than
             * max(anInt, maxEndHere + anInt), so
             * - if maxEndHere + anInt < anInt (i.e maxEndHere < 0)
             * and sum([x, i-1]) + anInt > anInt,
             * so sum([x, i-1]) must larger than maxEndHere -- contradiction
             * - if maxEndHere + anInt >= anInt
             * and sum([x, i-1]) + anInt > maxEndHere + anInt
             * so sum([x, i-1]) must larger than maxEndHere -- contradiction
             * In conclusion, max(anInt, maxEndHere + anInt) is next maxEndHere
             */
            maxSoFar = Math.max(maxSoFar, maxEndHere);
        }
        return maxSoFar;
    }
}
