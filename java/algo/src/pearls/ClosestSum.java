package pearls;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 6/23/16.
 * <p>
 * <h3>Des</h3>
 * find the sub-vector with the sum closest to a given real number, not allow empty
 * sub-vector
 * <p>
 * <h3>Variant</h3>
 * <li>not closest, but largest possible which smaller than aim(<=)</li>
 * <li>(>=)</li>
 * <li>not sub-vector, but sub-sequence</li>
 *
 * @see methodology.dynamic.MaxOfSumOpt
 * <p>
 * <h3>a thinking direction of sub-vector</h3>
 * <h3>pre-compute prefix sum</h3>
 * <p>then convert it into the 2-sum like problem</p>
 */
public class ClosestSum {

    private static class PrefixSum implements Comparable<PrefixSum> {
        private double sum;
        private int endI;

        public PrefixSum(double sum, int endI) {
            this.sum = sum;
            this.endI = endI;
        }

        public PrefixSum(double target) {
            this.sum = target;
        }

        @Override
        public int compareTo(PrefixSum o) {
            return Double.compare(sum, o.sum);
        }
    }

    /**
     * <h3>Answer for Variant</h3>
     * <li>not closest, but largest possible which smaller than aim(<=) -- only find floor element</li>
     * <li>(>=) -- only find ceiling element</li>
     * <li>not sub-vector, but sub-sequence
     * -- can't use this method, prefix sum is only apply to sub-vector</li>
     *
     * @return if aim is 0, allow choose empty sum, i.e. return 0
     */
    public static double closestSumPreCompute(final double[] nums, final double aim) {
        // set up pre computed array
        final int len = nums.length + 1;
        PrefixSum[] prefix = new PrefixSum[len];
        double nowSum = 0;
        prefix[0] = new PrefixSum(0, 0);
        for (int i = 0; i < nums.length; i++) {
            double num = nums[i];
            nowSum += num;
            prefix[i + 1] = new PrefixSum(nowSum, i + 1);
        }

        Arrays.sort(prefix);
        // find closest like 2-sum, but here is 2-difference
        double closestSoFar = Double.MAX_VALUE;
        double gap = Double.MAX_VALUE;
        for (int i = 0; i < prefix.length; i++) {
            PrefixSum now = prefix[i];
            double laterSum = aim + now.sum;

            // closest to match the gap
            final PrefixSum fake = new PrefixSum(laterSum);
            // not find back
            int target = Arrays.binarySearch(prefix, i + 1, len, fake);
            if (target >= 0) { // find the exact match
                return aim;
            } else {
                // change to insert point
                target = -target - 1;
                if (target > 0) { // has floor
                    final PrefixSum floor = prefix[target - 1];
                    if (floor.endI > now.endI) { // floor is in the back
                        final Double floorGap = laterSum - floor.sum;
                        if (gap > floorGap) {
                            gap = floorGap;
                            closestSoFar = floor.sum - now.sum;
                        }
                    }
                }
                if (target < len) { // has ceiling
                    PrefixSum ceiling = prefix[target];
                    if (ceiling.endI > now.endI) {
                        final Double ceilGap = ceiling.sum - laterSum;
                        if (gap > ceilGap) {
                            gap = ceilGap;
                            closestSoFar = ceiling.sum - now.sum;
                        }
                    }
                }
            }
        }
        return closestSoFar;
    }

    public static void main(String[] args) {
        double[] tmp = {1, 8, 2};
        test(tmp, 10);
        for (int i = 0; i < 10; i++) {
            final double[] nums = ArrayUtility.randomDoubles(12 + i, 5, -50, 50);
            test(nums, 10);
        }
    }

    private static void test(double[] nums, double aim) {
        System.out.println(Arrays.toString(nums));
        //        System.out.println(WrongClosestSum.closestDynamic(nums, aim));
        System.out.println(closestSumPreCompute(nums, aim));
    }
}
