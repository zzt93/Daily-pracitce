package competition.leetcode.w47;

import java.util.HashMap;

/**
 * Created by zzt on 8/27/17.
 * <p>
 * <h3></h3>
 */
public class PathSum4 {

    public int pathSum(int[] n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = n.length - 1; i >= 0; i--) {
            int extract = n[i] / 10;
            int mul = n[i] % 10;
            if (map.containsKey(extract)) {
                sum = map.get(extract) * mul + sum;
            } else {
                sum += mul;
            }
            sumUp(map, n[i]);
        }
        return sum;
    }

    private void sumUp(HashMap<Integer, Integer> map, int i) {
        int index = i / 10;
        int r = index % 10;
        int depth = index - r;
        if (depth <= 10) {
            return;
        }
        int fatherIndex = depth - 10 + (r + 1) / 2;
        map.put(fatherIndex, map.getOrDefault(fatherIndex, 0) + map.getOrDefault(index, 1));
    }

    public static void main(String[] args) {
        PathSum4 sum = new PathSum4();
        System.out.println(sum.pathSum(new int[]{113, 221, 332, 334}));
        System.out.println(sum.pathSum(new int[]{113, 215, 221}));
        System.out.println(sum.pathSum(new int[]{113, 221}));
    }
}
