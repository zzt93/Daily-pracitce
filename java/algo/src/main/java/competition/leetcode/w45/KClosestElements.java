package competition.leetcode.w45;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 8/13/17.
 * <p>
 * <h3></h3>
 */
public class KClosestElements {

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        int[] tmp = new int[k];
        int i = Collections.binarySearch(arr, x);
        int c = 0;
        int start;
        if (i >= 0) {
            tmp[c++] = arr.get(i);
            start = i;
        } else {
            int insert = -i - 1;
            int closer = closer(x, arr, insert - 1, insert);
            tmp[c++] = arr.get(closer);
            start = closer;
        }

        int back = 1;
        int forward = 1;
        while (c < k) {
            int closer = closer(x, arr, start - back, start + forward);
            tmp[c] = arr.get(closer);
            if (closer == start - back) {
                back++;
            } else {
                forward++;
            }
            c++;
        }
        Arrays.sort(tmp);
        return Arrays.stream(tmp).boxed().collect(Collectors.toList());
    }

    private int closer(int x, List<Integer> arr, int f, int s) {
        if (f < 0) {
            return s;
        }
        if (s >= arr.size()) {
            return f;
        }
        int g1 = x - arr.get(f);
        int g2 = arr.get(s) - x;
        return g1 < g2 ? f : (g1 == g2 ? f : s);
    }

    public static void main(String[] args) {
        KClosestElements elements = new KClosestElements();
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 4, 5), 4, 3));
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 4, 5), 4, -1));
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 4, 5), 4, 6));
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 5, 7, 9), 4,
                6));
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 5, 7, 9), 3,
                6));
        System.out.println(elements.findClosestElements(Lists.newArrayList(1, 2, 3, 5, 5, 5, 5,
                7, 9), 3, 6));
    }
}
