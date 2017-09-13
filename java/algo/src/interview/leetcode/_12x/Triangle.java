package interview.leetcode._12x;

import java.util.Comparator;
import java.util.List;

/**
 * Created by zzt on 9/13/17.
 * <p>
 * <h3></h3>
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            List<Integer> last = triangle.get(i - 1);
            row.set(0, row.get(0) + last.get(0));
            int size = row.size();
            for (int x = 1; x < size - 1; x++) {
                row.set(x, Math.min(last.get(x - 1), last.get(x)) + row.get(x));
            }
            row.set(size - 1, last.get(size - 2) + row.get(size - 1));
        }
        return triangle.get(triangle.size() - 1).stream().min(Comparator.naturalOrder()).orElse
                (0);
    }

}
