package methodology.dynamic;

import java.util.ArrayList;

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
 * <p>
 * <ul>convert to lcs</ul>
 * <li>sort the input to produce an increasing sequence</li>
 * <li>compute the lcs of this two sequence</li>
 */
public class LongestIncreasing {

    public static ArrayList<Integer> lis(int[] se) {
        ArrayList<Integer> res = new ArrayList<>();
        return res;
    }
}
