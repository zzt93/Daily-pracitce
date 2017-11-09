package interview.leetcode._15x;

/**
 * Created by zzt on 11/10/17.
 * <p>
 * <h3></h3>
 */
public class MinInRotatedSortedArray2 {

    public int findMin(int[] n) {
        int s = 0, e = n.length;
        while (s < e - 1) {
            if (n[s] < n[e - 1]) {
                return n[s];
            } else if (n[s] > n[e-1]){
                int mid = (e + s) / 2;
                if (n[mid] > n[s]) {
                    s = mid + 1;
                } else if (n[mid] < n[s]){
                    e = mid + 1;
                    s++;
                }
            } else {

            }
        }
        return n[s];
    }
}
