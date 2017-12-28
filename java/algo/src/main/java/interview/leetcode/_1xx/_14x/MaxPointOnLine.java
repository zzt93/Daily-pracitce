package interview.leetcode._1xx._14x;


import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by zzt on 11/3/17.
 * <p>
 * <h3></h3>
 */
public class MaxPointOnLine {

    public int maxPoints(Point[] points) {
        if (points.length <= 1) {
            return points.length;
        }
        int max = 0;
        for (int i = 0; i + 1 < points.length; i++) {
            int start = 1;
            TreeMap<Rate, Integer> c = new TreeMap<>();
            for (int x = i + 1; x < points.length; x++) {
                if (same(points[i], points[x])) {
                    start++;
                    continue;
                }
                Rate rate = new Rate(points[x], points[i]);
                if (c.containsKey(rate)) {
                    c.put(rate, c.get(rate) + 1);
                } else {
                    c.put(rate, 1);
                }
            }
            Integer m = c.values().stream().max(Comparator.naturalOrder()).orElse(0) + start;
            if (m > max) {
                max = m;
            }
        }
        return max;
    }

    private static class Rate implements Comparable<Rate> {
        private long dy;
        private long dx;

        public Rate(Point f, Point s) {
            this.dx = f.x - s.x;
            this.dy = f.y - s.y;
            if (dx < 0) {
                dx = -dx;
                dy = -dy;
            }
        }

        @Override
        public int compareTo(Rate o) {
            if (dx == 0) {
                if (o.dx == 0) {
                    assert o.dy != 0;
                    return 0;
                } else {
                    return 1;
                }
            } else if (dy == 0) {
                if (o.dy == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (o.dx == 0) {
                return -1;
            } else if (o.dy == 0) {
                return 1;
            }
            return Long.compare(dy * o.dx, dx * o.dy);
        }
    }

    private boolean same(Point f, Point s) {
        return f.x == s.x && s.y == f.y;
    }

    public static void main(String[] args) {
        MaxPointOnLine m = new MaxPointOnLine();
        System.out.println(m.maxPoints(new Point[]{new Point(-4, -4), new Point(-8, -582), new
                Point(-3, 3), new Point(-9, -651), new Point(9, 591)}));
        System.out.println(m.maxPoints(new Point[]{new Point(0, 0), new Point(94911151, 94911150)
                , new Point(94911152, 94911151)}));
        System.out.println(m.maxPoints(new Point[]{new Point(2, 3), new Point(3, 3), new Point
                (-5, 3)}));
        System.out.println(m.maxPoints(new Point[]{new Point(84, 250), new Point(0, 0), new Point
                (1, 0), new Point(0, -70), new Point(0, -70), new Point(1, -1), new Point(21, 10)
                , new Point(42, 90), new Point(-42, -230)}));
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
