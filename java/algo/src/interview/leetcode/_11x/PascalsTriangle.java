package interview.leetcode._11x;

import java.util.*;

/**
 * Created by zzt on 9/12/17.
 * <p>
 * <h3></h3>
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return Collections.emptyList();
        }
        ArrayList<List<Integer>> res = new ArrayList<>(numRows);
        res.add(new ArrayList<>(Arrays.asList(1)));
        for (int i = 1; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            LinkedList<Integer> last = new LinkedList<>(res.get(i - 1));
            last.addFirst(0);
            last.add(0);
            for (int x = 0; x < last.size() - 1; x++) {
                tmp.add(last.get(x) + last.get(x + 1));
            }
            res.add(tmp);
        }
        return res;
    }
}
