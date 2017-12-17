package interview.leetcode._1xx._16x;

/**
 * Created by zzt on 11/13/17.
 * <p>
 * <h3></h3>
 */
public class FindPeakElement {

    public int findPeakElement(int[] n) {
        for (int i = 0; i < n.length; i++) {
            while (i < n.length && !cmp(n, i)) {
                i++;
            }
            return i;
        }
        throw new AssertionError();
    }

    private boolean cmp(int[] n, int i) {
        boolean l = false, r = false;
        if (i == 0 || n[i] > n[i - 1]) {
            l = true;
        }
        if (i == n.length - 1 || n[i] > n[i + 1]) {
            r = true;
        }
        return l && r;
    }

    public static void main(String[] args) {
        FindPeakElement f = new FindPeakElement();
//        System.out.println(f.findPeakElement(new int[]{}));
        System.out.println(f.findPeakElement(new int[]{1}));
        System.out.println(f.findPeakElement(new int[]{1,2}));
        System.out.println(f.findPeakElement(new int[]{1,2,3}));
        System.out.println(f.findPeakElement(new int[]{1,2,3,1}));
        System.out.println(f.findPeakElement(new int[]{1,2,3,1,2,1}));
        System.out.println(f.findPeakElement(new int[]{3,2,1}));
        System.out.println(f.findPeakElement(new int[]{3,2,1,2}));
    }
}
