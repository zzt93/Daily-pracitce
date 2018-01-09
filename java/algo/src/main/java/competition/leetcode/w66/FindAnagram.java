package competition.leetcode.w66;

import java.util.HashMap;

/**
 * Created by zzt on 1/7/18.
 * <p>
 * <h3></h3>
 */
public class FindAnagram {

    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = map.get(A[i]);
        }
        return res;
    }
}
