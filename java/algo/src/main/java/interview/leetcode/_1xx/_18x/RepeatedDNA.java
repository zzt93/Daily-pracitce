package interview.leetcode._1xx._18x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzt on 11/18/17.
 * <p>
 * <h3></h3>
 */
public class RepeatedDNA {

    public List<String> findRepeatedDnaSequences(String s) {
        char[] cs = s.toCharArray();
        Set<String> seen = new HashSet<>(), res = new HashSet<>();
        for (int i = 0; i < cs.length - 9; i++) {
            String t = new String(cs, i, 10);
            if (seen.contains(t)) {
                res.add(t);
            } else {
                seen.add(t);
            }
        }
        return new ArrayList<>(res);
    }
}
