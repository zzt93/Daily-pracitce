package competition.leetcode.w33;

import java.util.HashMap;

/**
 * Created by zzt on 5/21/17.
 * <p>
 * <h3></h3>
 */
public class ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // four edges equal
        double ll[] = new double[6];
        ll[0] = len(p2, p4);
        ll[1] = len(p1, p2);
        ll[2] = len(p3, p2);
        ll[3] = len(p3, p4);
        ll[4] = len(p1, p4);
        ll[5] = len(p1, p3);
        HashMap<Double, Integer> map = new HashMap<>();
        for (double v : ll) {
            if (v == 0) {
                return false;
            }
            if (map.containsKey(v)) {
                map.put(v, map.get(v) + 1);
            } else {
                map.put(v, 1);
            }
        }
        if (map.size() == 2) {
            return map.values().stream().findFirst().filter(integer -> integer == 2 || integer == 4).isPresent();
        }
        return false;
    }


    private double len(int[] p1, int[] p2) {
        return
                Math.pow(p1[0] - p2[0], 2) +
                        Math.pow(p1[1] - p2[1], 2);
    }

    public static void main(String[] args) {
        ValidSquare validSquare = new ValidSquare();
        System.out.println(validSquare.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}));
        System.out.println(validSquare.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, -1}, new int[]{2, 0}));
    }
}
