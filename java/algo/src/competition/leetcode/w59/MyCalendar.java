package competition.leetcode.w59;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 */
public class MyCalendar {

    private final TreeSet<int[]> ranges;

    public MyCalendar() {
        ranges = new TreeSet<>(Comparator.comparingInt(is -> is[0]));
    }

    public boolean book(int start, int end) {
        int[] ints = {start, end};
        if (ranges.contains(ints)) {
            return false;
        }
        int[] higher = ranges.higher(ints);
        int[] lower = ranges.lower(ints);
        if (lower != null && lower[1] > start) {
            return false;
        }
        if (higher != null && end > higher[0]) {
            return false;
        }
        ranges.add(ints);
        return true;
    }
}
