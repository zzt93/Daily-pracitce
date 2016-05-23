package pearls;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <h3>Des</h3>
 * find the sub-vector with the sum closest to a given real number, not allow empty
 * sub-vector
 *
 * <h3>Solution</h3>
 * same as max sub-vector
 *
 * <h3>Thought</h3>
 * all compute a new state easily from old state(from one dimension to more)
 *
 * @see methodology.dynamic.MaxOfSum
 * @see methodology.dynamic.ShortestPathWithMoney
 */
public class ClosestSum {

    public static double closestDynamic(double[] doubles, double aim) {
        double closestForNow = aim + Double.MAX_VALUE;
        double closestEndHere = 0;
        for (double num : doubles) {
            closestEndHere = closest(aim, closestEndHere + num, num);
            closestForNow = closest(aim, closestEndHere, closestForNow);
        }
        return closestForNow;
    }

    private static double closest(double aim, double closestEndHere, double closest) {
        double g1 = Math.abs(closest - aim);
        double g2 = Math.abs(closestEndHere - aim);
        if (g1 < g2) {
            return closest;
        }
        return closestEndHere;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final double[] nums = ArrayUtility.randomDoubles(12 + i, 5, -50, 50);
            System.out.println(Arrays.toString(nums));
            System.out.println(closestDynamic(nums, 0));
        }
    }
}
