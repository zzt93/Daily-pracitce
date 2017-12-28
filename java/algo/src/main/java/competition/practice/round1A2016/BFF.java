package competition.practice.round1A2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zzt on 3/28/15.
 * <p/>
 * Wrong solutiongg
 */
@Deprecated()
public class BFF {


    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/bff-small-practice0.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        int res;
        for (int i = 0; i < trail; i++) {
            in.nextInt();
            res = bffCount(in.oneLineToInt(" "));
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static int bffCount(ArrayList<Integer> integers) {
        // count appearance
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (Integer integer : integers) {
            Integer c = counts.get(integer);
            counts.put(integer, (c == null) ? 1 : c + 1);
        }
        int max = -1;
        NEXT:
        for (int i = 0; i < integers.size(); i++) {
            HashSet<Integer> indexes = new HashSet<>();
            int start = i + 1;
            int res;
            LinkedList<Integer> circle = new LinkedList<>();
            int next = start;
            do {
                circle.add(next);
                indexes.add(next);
                next = integers.get(next - 1);
                if (next - 1 < i) {
                    continue NEXT;
                }
            } while (start != next && (circle.size() < 2 || circle.get(circle.size() - 2) != next)
                    && !indexes.contains(next));
            assert start == circle.getFirst();
            if (next != start &&
                    (circle.size() < 2 || circle.get(circle.size() - 2) != next) &&
                    indexes.contains(next)) {
                continue;
            }
            res = circle.size();
            if (circle.size() >= 2 && circle.get(circle.size() - 2) == next) {
                if (counts.get(circle.getFirst()) != null) {
                    res++;
                } else if (counts.get(circle.getLast()) > 1) {
                    res++;
                }
            }
            if (res > max) {
                max = res;
            }
            if (max >= integers.size() - i) {
                return max;
            }
        }
        return max;
    }
}
