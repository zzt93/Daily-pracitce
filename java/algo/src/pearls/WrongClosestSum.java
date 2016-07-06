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
 * <h3>Can't use the same way with max sum problem, e.g. {1, 8, 2}, aim = 10</h3>
 *
 * @see methodology.dynamic.MaxOfSumOpt
 * @see methodology.dynamic.ShortestPathWithMoney
 */
@Deprecated
public class WrongClosestSum {

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

}
