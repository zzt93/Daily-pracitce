package competition.leetcode.w66;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 1/7/18.
 * <p>
 * <h3></h3>
 */
public class FreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> list = avails.stream().flatMap(List::stream).sorted((i1, i2) -> {
            int compare = Integer.compare(i1.start, i2.start);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(i1.end, i2.end);
        }).collect(Collectors.toList());
        LinkedList<Interval> stack = new LinkedList<>();
        List<Interval> res = new ArrayList<>();
        stack.addLast(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            Interval last = stack.peekLast();
            Interval now = list.get(i);
            if (last.end >= now.start && last.end <= now.end) {
                stack.removeLast();
                stack.addLast(new Interval(last.start, now.end));
            } else if (last.end > now.end) {
            } else if (last.end < now.start) {
                res.add(new Interval(last.end, now.start));
                stack.addLast(now);
            }
        }
        return res;
    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
