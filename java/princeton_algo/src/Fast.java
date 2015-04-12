
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zzt on 2/23/15.
 */
public class Fast {
    private static final int LEN = 4;

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int size = in.readInt();
        Point[] points = new Point[size];
//        boolean[] find = new boolean[size];
        for (int i = 0; i < size; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            //find[i] = false;
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            points[i].draw();
        }
        Arrays.sort(points, 0, points.length);

        for (int i = 0; i + LEN -1 < points.length; i++) {

            if (points[i].isFind()) {
                continue;
            }
            Comparator<Point> temp = points[i].SLOPE_ORDER;
            Arrays.sort(points, i + 1, points.length, temp);

            int count = 1;
            double last = points[i+1].slopeTo(points[i]);
            int i1;
            for (i1 = i + 2; i1 < points.length; i1++) {
                if (last == points[i1].slopeTo(points[i])) {
                    count++;
                } else {
                    last = points[i1].slopeTo(points[i]);
                    if (count >= LEN - 1) { //plus points[i] meet the requirements
                        System.out.print(points[i].toString());
                        points[i].setFind(true);
                        for (int i2 = count - 1; i2 >= 0; i2--) {
                            System.out.print("->");
                            System.out.print(points[i1-i2-1]);
                            points[i1-i2-1].setFind(true);
                        }
                        System.out.println();
                        StdDraw.setXscale(0, 32768);
                        StdDraw.setYscale(0, 32768);
                        points[i].drawTo(points[i1-1]);
                    }
                    count = 1;
                }
            }
            if (count >= LEN - 1) { //plus points[i] meet the requirements
                System.out.print(points[i].toString());
                points[i].setFind(true);
                for (int i2 = count - 1; i2 >= 0; i2--) {
                    System.out.print("->");
                    System.out.print(points[i1-i2-1]);
                    points[i1-i2-1].setFind(true);
                }
                System.out.println();
                StdDraw.setXscale(0, 32768);
                StdDraw.setYscale(0, 32768);
                points[i].drawTo(points[i1-1]);
            }

            Arrays.sort(points, i+1, points.length);
        }
    }
}
