package interview.leetcode._11x;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 9/12/17.
 * <p>
 * <h3></h3>
 */
public class PascalsTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        LinkedList<Integer> res = new LinkedList<>(Arrays.asList(0, 1, 0));
        for (int i = 0; i < rowIndex; i++) {
            for (int x = 1; x < res.size(); x++) {
                res.set(x - 1, res.get(x - 1) + res.get(x));
            }
            res.addFirst(0);
        }
        res.removeFirst();
        res.removeLast();
        return res;
    }
}
