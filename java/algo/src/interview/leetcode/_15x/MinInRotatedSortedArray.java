package interview.leetcode._15x;

/**
 * Created by zzt on 11/9/17.
 * <p>
 * <h3></h3>
 */
public class MinInRotatedSortedArray {

    public int findMin(int[] n) {
        int s = 0, e = n.length;
        while (s < e - 1) {
            if (n[s] < n[e - 1]) {
                return n[s];
            } else {
                assert n[s] > n[e - 1];
                int mid = (e + s) / 2;
                if (n[mid] > n[s]) {
                    s = mid + 1;
                } else {
                    assert n[mid] < n[s];
                    e = mid + 1;
                    s++;
                }
            }
        }
        return n[s];
    }

    public static void main(String[] args) {
        MinInRotatedSortedArray m = new MinInRotatedSortedArray();
        //        System.out.println(m.findMin(new int[]{}));
        System.out.println(m.findMin(new int[]{2, 1}));
        System.out.println(m.findMin(new int[]{1}));
        System.out.println(m.findMin(new int[]{1, 2}));
        System.out.println(m.findMin(new int[]{1, 2, 3}));
        System.out.println(m.findMin(new int[]{2, 3, 1}));
        System.out.println(m.findMin(new int[]{2, 3, 0, 1}));
    }
}
