package competition.leetcode.w47;

/**
 * Created by zzt on 8/27/17.
 * <p>
 * <h3></h3>
 */
public class NonDecreasingArray {

    public boolean checkPossibility(int[] n) {
        int c = 0, x = -1;
        for (int i = 0; i < n.length - 1; i++) {
            if (n[i] > n[i + 1]) {
                c++;
                x = i;
            }
            if (c > 1) {
                return false;
            }
        }
        if (c == 0) {
            return true;
        }
        if (x == 0 || x + 1 == n.length - 1) {
            return true;
        }
        if (n[x - 1] <= n[x + 1] || n[x] <= n[x + 2]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        NonDecreasingArray array = new NonDecreasingArray();
        System.out.println(array.checkPossibility(new int[]{1, 2, 3}));
        System.out.println(array.checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(array.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(array.checkPossibility(new int[]{4, 2, 1}));
        System.out.println(array.checkPossibility(new int[]{4}));
    }
}
