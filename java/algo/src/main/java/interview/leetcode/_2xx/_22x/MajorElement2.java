package interview.leetcode._2xx._22x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 12/26/17.
 * <p>
 * <h3></h3>
 */
public class MajorElement2 {

    public List<Integer> majorityElement(int[] n) {
        if (n.length == 0) {
            return Collections.emptyList();
        }
        int n1 = n[0], n2 = n[0], c1 = 0, c2 = 0;
        for (int i : n) {
            if (i == n1) c1++;
            else if (i == n2) c2++;
            else if (c1 == 0) {
                n1 = i;
                c1++;
            } else if (c2 == 0) {
                n2 = i;
                c2++;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = c2 = 0;
        for (int i : n) {
            if (i == n1) c1++;
            else if (i == n2) c2++;
        }
        List<Integer> res = new ArrayList<>();
        if (c1 > n.length / 3)
            res.add(n1);
        if (c2 > n.length / 3)
            res.add(n2);
        return res;
    }

}
