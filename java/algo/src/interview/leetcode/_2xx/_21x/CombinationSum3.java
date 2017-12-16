package interview.leetcode._2xx._21x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 12/14/17.
 * <p>
 * <h3></h3>
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k > 9 || n > 55) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new LinkedList<>();
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        cmn(res, a, 0, k, n, new LinkedList<>());
        return res;
    }

    private void cmn(List<List<Integer>> res, int[] a, int i, int k, int n, LinkedList<Integer>
            integers) {
        if (integers.size() == k) {
            if (sum(integers) == n) {
                res.add(new ArrayList<>(integers));
            }
            return;
        }
        if (i >= a.length) return;
        integers.add(a[i]);
        cmn(res, a, i + 1, k, n, integers);
        integers.removeLast();
        cmn(res, a, i + 1, k, n, integers);
    }

    private int sum(LinkedList<Integer> integers) {
        int res = 0;
        for (Integer integer : integers) {
            res += integer;
        }
        return res;
    }

    public static void main(String[] args) {
        CombinationSum3 c = new CombinationSum3();
        System.out.println(c.combinationSum3(6, 34));
    }
}
