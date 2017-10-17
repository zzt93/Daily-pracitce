package interview.leetcode._13x;

/**
 * Created by zzt on 10/16/17.
 * <p>
 * <h3></h3>
 */
public class GasStation {

    public int canCompleteCircuitOld(int[] gas, int[] cost) {
        int n = cost.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }

        int res;
        for (int s = 0; s < n; s++) {
            res = s;
            int g = gas[s];
            for (int next = s + 1, c = 0; c < n; c++) {
                if (next == n) {
                    next = 0;
                    if (g >= cost[n - 1]) {
                        g = gas[0] + g - cost[n - 1];
                    } else {
                        res = -1;
                        break;
                    }
                    if (next == s) {
                        break;
                    }
                } else {
                    if (g >= cost[next - 1]) {
                        g = gas[next] + g - cost[next - 1];
                    } else {
                        res = -1;
                        break;
                    }
                }
                next++;
            }
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {

        return -1;
    }

    public static void main(String[] args) {
        GasStation station = new GasStation();
        System.out.println(station.canCompleteCircuit(new int[]{2,4}, new int[]{3,4}));
        System.out.println(station.canCompleteCircuit(new int[]{2, 0, 1, 2, 3, 4, 0}, new
                int[]{0, 1, 0, 0, 0, 0, 11}));
        System.out.println(station.canCompleteCircuit(new int[]{6}, new int[]{5}));
        System.out.println(station.canCompleteCircuit(new int[]{}, new int[]{}));
        System.out.println(station.canCompleteCircuit(new int[]{4}, new int[]{5}));
        System.out.println(station.canCompleteCircuit(new int[]{6, 7}, new int[]{7, 6}));
        System.out.println(station.canCompleteCircuit(new int[]{6, 7, 5}, new int[]{7, 6, 5}));
        System.out.println(station.canCompleteCircuit(new int[]{6, 7, 5}, new int[]{7, 6, 6}));
    }
}
