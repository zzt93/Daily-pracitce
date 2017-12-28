package interview.leetcode._2xx._21x;

import java.util.*;

/**
 * Created by zzt on 12/16/17.
 * <p>
 * <h3>Two dimension problem</h3>
 * <h3>Range Problem: find max in range</h3>
 * <ul>
 *     <li></li>
 * </ul>
 */
public class SkyLine {

    public List<int[]> getSkyline(int[][] buildings) {
        TreeMap<Integer, Integer> height = new TreeMap<>();
        TreeMap<Integer, List<Integer>> axis = new TreeMap<>();
        for (int[] building : buildings) {
            axis.computeIfAbsent(building[0], k -> new LinkedList<>()).add(building[2]);
            axis.computeIfAbsent(building[1], k -> new LinkedList<>()).add(-building[2]);
        }

        List<int[]> res = new ArrayList<>();
        int hei = -1;
        for (Map.Entry<Integer, List<Integer>> ints : axis.entrySet()) {
            for (Integer h : ints.getValue()) {
                if (h > 0) {
                    height.put(h, height.getOrDefault(h, 0)+1);
                } else if (h < 0) {
                    Integer count = height.get(-h);
                    Integer ignore = count == 1 ? height.remove(-h) : height.put(-h, count - 1);
                }
            }
            int tmp = height.isEmpty() ? 0: height.lastKey();
            if (tmp != hei) {
                hei = tmp;
                res.add(new int[]{ints.getKey(), hei});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SkyLine s = new SkyLine();
        System.out.println(s.getSkyline(new int[][]{new int[]{0,3,3},new int[]{1,5,3},new int[]{2,4,3},new int[]{3,7,3}}));
        System.out.println(s.getSkyline(new int[][]{new int[]{0,2,3},new int[]{2,5,3}}));
        System.out.println(s.getSkyline(new int[][]{new int[]{0,2,3},new int[]{2,5,3},new int[]{5,7,3}}));
        System.out.println(s.getSkyline(new int[][]{new int[]{2,9,10},new int[]{3,7,15},new int[]{5,12,12},new int[]{15,20,10},new int[]{19,24,8}}));
    }
}
