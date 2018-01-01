package competition.leetcode.w65;

import java.util.Arrays;

/**
 * Created by zzt on 12/31/17.
 * <p>
 * <h3></h3>
 */
public class PourWater {

    public int[] pourWater(int[] h, int V, int K) {
        for (int c = 0; c < V; c++) {
            int minI = K, min = h[K];
            for (int i = K - 1; i >= 0; i--) {
                if (h[i] > h[i + 1]) {
                    break;
                } else if (h[i] < min) {
                    min = h[i];
                    minI = i;
                }
            }
            if (minI != K) {
                h[minI]++;
                continue;
            }
            for (int i = K + 1; i < h.length; i++) {
                if (h[i - 1] < h[i]) {
                    break;
                } else if (h[i] < min) {
                    min = h[i];
                    minI = i;
                }
            }
            h[minI]++;
        }
        return h;
    }

    public static void main(String[] args) {
        PourWater p = new PourWater();
        System.out.println(Arrays.toString(p.pourWater(new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1}, 2, 5)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{3, 1, 3}, 5, 1)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1, 2, 3, 4}, 2, 2)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1}, 0, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1}, 200, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1, 2, 3, 4}, 200, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1, 2, 3, 4}, 200, 3)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{4, 3, 2, 1}, 200, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{4, 3, 2, 1}, 200, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{4, 3, 2, 1}, 200, 3)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1, 2, 3, 4, 3, 2, 1}, 200, 0)));
        System.out.println(Arrays.toString(p.pourWater(new int[]{1, 2, 3, 4, 3, 2, 1}, 200, 6)));
    }
}
