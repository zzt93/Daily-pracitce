package competition.leetcode.w76;

/**
 * Created by zzt on 3/18/18.
 * <p>
 * <h3></h3>
 */
public class SimilarRGB {

    public String similarRGB(String color) {
        int[] col = new int[]{Integer.parseInt(color.substring(1, 3),16),
                Integer.parseInt(color.substring(3, 5), 16),
                Integer.parseInt(color.substring(5, 7),16),
        };
        int max = Integer.MIN_VALUE;
        String res = "";
        for (int i = 0; i < 16; i++) {
            for (int x = 0; x < 16; x++) {
                for (int k = 0; k < 16; k++) {
                    int tmp = cal(new int[]{i, x, k}, col);
                    if (tmp > max) {
                        res = "#" + Integer.toHexString(i) + Integer.toHexString(i)
                                + Integer.toHexString(x) + Integer.toHexString(x)
                                + Integer.toHexString(k) + Integer.toHexString(k);
                        max = tmp;
                    }
                }
            }
        }
        return res;
    }

    private int cal(int[] n, int[] col) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res += (int) Math.pow(n[i] * 16 + n[i] - col[i], 2);
        }
        return -res;
    }

    public static void main(String[] args) {
        SimilarRGB s = new SimilarRGB();
        System.out.println(s.similarRGB("#09f166"));
    }
}
