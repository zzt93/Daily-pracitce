package competition.leetcode.w59;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3>Range problem:</h3>
 * <li>Segment tree -- find point in range: static structure, every node has range and the interval it belongs to</li>
 * <li>Interval tree -- find range in range</li>
 * <li>Axis: convert range to point (1) start is 1, end is -1, count from left to right (2) remember count at point </li>
 */
public class MyCalendarTwo {

    private final TreeSet<int[]> levelTwoS = new TreeSet<>(Comparator.comparingInt(is -> is[0]));
//    private final TreeSet<int[]> levelTwoE = new TreeSet<>(Comparator.comparingInt(is -> is[1]));
    private final TreeSet<int[]> levelOneS = new TreeSet<>(Comparator.comparingInt(is -> is[0]));
    private final TreeSet<int[]> levelOneE = new TreeSet<>(Comparator.comparingInt(is -> is[1]));

    public MyCalendarTwo() {
    }

    @Deprecated
    public boolean bookWrong(int start, int end) {
        int[] ints = {start, end};
        int[] higher = levelTwoS.ceiling(ints);
        int[] lower = levelTwoS.lower(ints);
        if (lower != null && lower[1] > start) {
            return false;
        }
        if (higher != null && end > higher[0]) {
            return false;
        }

        int[] ss = {start, 0};
        int[] se = {end, 0};
        int[] es = {0, start + 1};
        int[] ee = {0, end + 1};
        // lack one situation, (start,end) is entirely enclosed by another range
        SortedSet<int[]> los = levelOneS.subSet(ss, se);
        SortedSet<int[]> loe = levelOneE.subSet(es, ee);
        if (!los.isEmpty()
                || !loe.isEmpty()) {
            for (int[] lo : los) {
                levelTwoS.add(new int[]{lo[0], Math.min(lo[1], end)});
//                levelTwoE.add(new int[]{0, Math.min(lo[1], end)});
            }
            for (int[] lo : loe) {
                levelTwoS.add(new int[]{Math.max(lo[0],start), lo[1]});
//                levelTwoE.add(new int[]{0, lo[1]});
            }
        }
        levelOneS.add(ints);
        levelOneE.add(ints);
        return true;
    }

    private final TreeMap<int[], Integer> axis = new TreeMap<>((is1,is2) -> {
        if (is1[0] != is2[0]) return Integer.compare(is1[0],is2[0]);
        return Integer.compare(is1[1],is2[1]);
    });
    public boolean book(int start, int end) {
        int[] s = {start, 1};
        int[] e = {end, -1};
        if (axis.containsKey(s)) {
            axis.put(s, axis.get(s)+1);
        } else {
            axis.put(s, 1);
        }
        if (axis.containsKey(e)) {
            axis.put(e, axis.get(e)+1);
        } else {
            axis.put(e, 1);
        }
        int sum = 0;
        for (int[] key : axis.keySet()) {
            sum += (key[1] * axis.get(key));
            if (sum >= 3) {
                axis.put(s, axis.get(s)-1);
                axis.put(e, axis.get(e)-1);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo m = new MyCalendarTwo();
        System.out.println(m.book(10, 20)); // returns true
//        System.out.println(m.book(10, 20)); // returns true
//        System.out.println(m.book(11, 15)); // returns false
        System.out.println(m.book(50, 60)); // returns true
        System.out.println(m.book(10, 40)); // returns true
        System.out.println(m.book(5, 15)); // returns false
        System.out.println(m.book(5, 10)); // returns true
        System.out.println(m.book(25, 55)); // returns true
//        System.out.println(m.book(39, 56));
//                System.out.println(m.book(20, 25));
//        System.out.println(m.book(40, 50));
//        System.out.println(m.book(55, 60));
//        System.out.println(m.book(5, 10));
//        System.out.println(m.book(55, 65));
    }
}
