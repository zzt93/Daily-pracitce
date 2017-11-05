package competition.leetcode.w57;

import java.util.List;

/**
 * Created by zzt on 11/5/17.
 * <p>
 * <h3></h3>
 */
public class RemoveComments {

    public List<String> removeComments(String[] source) {
        boolean multi = false;
        for (String s : source) {
            char[] cs = s.toCharArray();
            boolean last = false;
            for (char c : cs) {
                if (c == '/') {
                    last = true;
                } else {

                }
            }
        }
        return null;
    }
}
