package competition.leetcode.w58;

/**
 * Created by zzt on 11/12/17.
 * <p>
 * <h3></h3>
 */
public class FindPivotIndex {

    public int pivotIndex(int[] n) {
        if (n.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
        }
        int s = 0;
        for (int i = 0; i < n.length; i++) {
            int x = n[i];
            s += x;
            if (s * 2 == x + sum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindPivotIndex f = new FindPivotIndex();
        System.out.println(f.pivotIndex(new int[]{}));
        System.out.println(f.pivotIndex(new int[]{1}));
        System.out.println(f.pivotIndex(new int[]{1,2,3}));
        System.out.println(f.pivotIndex(new int[]{1,7,3,6,5,6}));
        System.out.println(f.pivotIndex(new int[]{1,7,3,0,0,5,6}));
    }
}
