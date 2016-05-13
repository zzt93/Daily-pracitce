package methodology.dynamic;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 5/11/16.
 * <p>
 * <h3>Des:</h3>
 * <p>
 * The Longest Increasing Sub-sequence problem is
 * to find the longest increasing subsequence of a given sequence.
 * Given a sequence S= {a1 , a2 , a3, a4, ..., an-1, an }
 * we have to find a longest subset such that for all j and i, j<i in the subset aj<ai.
 * </p>
 * <h3>Thought:</h3>
 * <ul>eliminate useless comparison:</ul>
 * <li>if already found sequence a1,..., ak, a2 ~ ak need
 * not to be the start anymore.
 * </li>
 * <li>Constraint: in the worst case( in reverse order), time complexity is still O(n^2)</li>
 * <li>Wrong: longer lis is calculated from short one, so can't make this</li>
 * <p>
 * <ul>convert to lcs</ul>
 * <li>sort the input to produce an increasing sequence</li>
 * <li>compute the lcs of this two sequence</li>
 *
 * <ul>dynamic way</ul>
 * <li>provided lis[0...i], lis[i+1] can be computed by try to become the last
 * element of all previous lis
 * </li>
 */
public class LongestIncreasing {

    public static int lis(Integer[] se) {
        int res = 0;
        int[] lis = new int[se.length];
        lis[0] = 1;
        for (int last = 1; last < se.length; last++) {
            for (int j = 0; j < last; j++) {
                if (se[last] > se[j] && lis[last] < lis[j] + 1) {
                    lis[last] = lis[j] + 1;
                }
            }
        }
        for (int i : lis) {
            if (res < i) {
                res = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Integer[] ints = ArrayUtility.randomIntegers(23, 10, 0, 1000);
        System.out.println(Arrays.toString(ints));
        System.out.println(lis(ints));
    }
}
