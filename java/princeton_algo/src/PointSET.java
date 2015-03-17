import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zzt on 3/9/15.
 */
public class PointSET {
    private SET<Point2D> set = new SET<Point2D>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D point2D) {
        nullCheck(point2D);
        set.add(point2D);
    }

    public boolean contains(Point2D point2D) {
        nullCheck(point2D);
        return set.contains(point2D);
    }

    public void draw() {
        for (Point2D point2D : set) {
            point2D.draw();
        }
    }

    public Iterable<Point2D> range(final RectHV rectHV) {
        nullCheck(rectHV);

        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {

                ArrayList<Point2D> res = new ArrayList<Point2D>();
                for (Point2D point2D : set) {
                    if (rectHV.contains(point2D)) {
                        res.add(point2D);
                    }
                }
                return res.iterator();
            }
        };
    }

    public Point2D nearest(Point2D point2D) {
        nullCheck(point2D);

        Point2D min = null;
        double minNum = Double.MAX_VALUE;
        for (Point2D d : set) {
            double tmp = d.distanceSquaredTo(point2D);
            if (tmp < minNum) {
                min = d;
                minNum = tmp;
            }
        }
        return min;
    }

    private void nullCheck(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {

    }
}
