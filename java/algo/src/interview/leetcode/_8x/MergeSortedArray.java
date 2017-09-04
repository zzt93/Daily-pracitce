package interview.leetcode._8x;

import java.util.Arrays;

/**
 * Created by zzt on 7/25/17.
 * <p>
 * <h3></h3>
 */
public class MergeSortedArray {

    public void merge(int[] n1, int m, int[] n2, int n) {
        if (n2.length == 0) return;
        noExtraSpace(n1, m, n2, n);
    }

    private void noExtraSpace(int[] n1, int m, int[] n2, int n) {
        for (int i = 0; i < m; i++) {
            if (n1[i] <= n2[0]) {
            } else {
                swap(n1, i, n2, 0);
                for (int x = 1; x < n; x++) {
                    if (n2[x] < n2[x - 1]) {
                        swap(n2, x, n2, x - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        System.arraycopy(n2, 0, n1, m, n);
    }

    private void swap(int[] n1, int i, int[] n2, int j) {
        int t = n1[i];
        n1[i] = n2[j];
        n2[j] = t;
    }

    public static void main(String[] args) {
        MergeSortedArray array = new MergeSortedArray();
        int[] n1 = new int[6];
        n1[0] = 1;
        n1[1] = 3;
        n1[2] = 5;
        int[] n2 = new int[3];
        n2[0] = 2;
        n2[1] = 4;
        n2[2] = 5;
        array.merge(n1, 3, n2, 3);
        System.out.println(Arrays.toString(n1));
    }
}
