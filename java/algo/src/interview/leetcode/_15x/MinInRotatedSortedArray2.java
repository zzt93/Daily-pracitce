package interview.leetcode._15x;

/**
 * Created by zzt on 11/10/17.
 * <p>
 * <h3></h3>
 */
public class MinInRotatedSortedArray2 {

    public int findMin(int[] n) {
        int s = 0, e = n.length - 1;
        while (s < e) {
            int mid = (e + s) / 2;
            if (n[mid] > n[e]) {
                s = mid + 1;
            } else if (n[mid] < n[e]) {
                e = mid;
            } else { // mid == e
                e--;
            }
        }
        return n[e];
    }

    public static void main(String[] args) {
        MinInRotatedSortedArray2 m = new MinInRotatedSortedArray2();
        System.out.println(m.findMin(new int[]{4, 4, 5, 6, 7, 1, 2, 3, 4}));
        System.out.println(m.findMin(new int[]{3, 1, 3, 3}));
        System.out.println(m.findMin(new int[]{3, 3}));
        System.out.println(m.findMin(new int[]{3, 3, 1}));
        System.out.println(m.findMin(new int[]{3, 3, 1, 3}));
        System.out.println(m.findMin(new int[]{3, 1, 3}));
        System.out.println(m.findMin(new int[]{3, 3, 3, 3, 3, 3, 1}));
        System.out.println(m.findMin(new int[]{2, 1}));
        System.out.println(m.findMin(new int[]{1}));
        System.out.println(m.findMin(new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println(m.findMin(new int[]{1, 2}));
        System.out.println(m.findMin(new int[]{1, 2, 3, 4, 4, 4, 5, 6, 7}));
        System.out.println(m.findMin(new int[]{1, 1, 1, 1, 1, 2}));
        System.out.println(m.findMin(new int[]{1, 2, 2, 2, 2, 2}));
        System.out.println(m.findMin(new int[]{1, 2, 3}));
        System.out.println(m.findMin(new int[]{2, 3, 1}));
        System.out.println(m.findMin(new int[]{2, 3, 0, 1}));
        System.out.println(m.findMin(new int[]{2, 2, 3, 0, 0, 1, 1}));
    }
}
