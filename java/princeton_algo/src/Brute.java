
import java.util.Arrays;

/**
 * Created by zzt on 2/23/15.
 */
public class Brute {

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        String filename = args[0];
        In in = new In(filename);
        int size = in.readInt();
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            points[i].draw();
        }

        Arrays.sort(points, 0, points.length);
        for (int i = 0; i < points.length; i++) {
            for (int i1 = i + 1; i1 < points.length; i1++) {
                for (int i2 = i1 + 1; i2 < points.length; i2++) {
                    for (int i3 = i2 + 1; i3 < points.length; i3++) {
                        double s12 = points[i1].slopeTo(points[i]);
                        double s13 = points[i2].slopeTo(points[i]);
                        double s14 = points[i3].slopeTo(points[i]);

                        if (s12 == s13 && s12 == s14) {

                            System.out.println(points[i].toString()
                                    + "->" + points[i1].toString() + "->"
                                    + points[i2].toString() + "->" + points[i3]);
                            StdDraw.setXscale(0, 32768);
                            StdDraw.setYscale(0, 32768);
                            points[i].drawTo(points[i3]);
                        }
                    }
                }
            }
        }
    }
}
