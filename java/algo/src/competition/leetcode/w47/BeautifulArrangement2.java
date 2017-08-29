package competition.leetcode.w47;

import java.util.Arrays;

/**
 * Created by zzt on 8/27/17.
 * <p>
 * <h3></h3>
 */
public class BeautifulArrangement2 {

    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        // set last n-(k+1)
        for (int i = k + 1; i < n; i++) {
            res[i] = i + 1;
        }
        // set small
        for (int i = 0; i < k + 1; i += 2) {
            res[i] = i / 2 + 1;
        }
        // set large
        for (int i = 1; i < k + 1; i += 2) {
            res[i] = k + 1 - i / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        BeautifulArrangement2 arr = new BeautifulArrangement2();
        int n = 7;
        for (int i = 1; i < n; i++) {
            System.out.println(Arrays.toString(arr.constructArray(n, i)));
        }
    }
}
