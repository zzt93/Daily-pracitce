import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zzt on 3/9/15.
 */
public class KdTree {

    private final static double EPSILON = 0.00001;
    private class Node {
        private Point2D point2D;
        private Node left;
        private Node right;
        private boolean horizontal;

        Node(Point2D point2D, Node left, Node right, boolean horizontal) {
            this.point2D = point2D;
            this.left = left;
            this.right = right;
            this.horizontal = horizontal;
        }

        public double toComp() {
            if (!horizontal) {
                return point2D.x();
            } else {
                return point2D.y();
            }
        }

        /*
        distance to the split line
         */
        public double disToline(Point2D point2D) {
            Point2D tmp;
            if (!horizontal) {
                tmp = new Point2D(this.point2D.x(), point2D.y());
            } else {
                tmp = new Point2D(point2D.x(), this.point2D.y());
            }
            return point2D.distanceTo(tmp);
        }

        public boolean small(Point2D aim) {
            if (horizontal) {
                return point2D.y() < aim.y();
            }
            return point2D.x() < aim.x();
        }

        public boolean rectInSmall(RectHV rectHV) {
            if (horizontal) {
                return (rectHV.ymax() + rectHV.ymin() <= 2 * point2D.y());
            }
            return (rectHV.xmax() + rectHV.xmin() <= 2 * point2D.x());
        }

        public boolean isCross(RectHV rectHV) {
            Point2D central = new Point2D( (rectHV.xmax() + rectHV.xmin())/2, (rectHV.ymax() + rectHV.ymin())/2 );
            double toSplit = disToline(central);
            double toEdge;
            if (horizontal) {
                toEdge = (rectHV.ymax() - rectHV.ymin())/2;
                //toEdge = central.distanceTo( new Point2D( (rectHV.xmax() + rectHV.xmin())/2, rectHV.ymax()) );
                //assert rectHV.height() == toEdge*2;
            } else {
                //toEdge = central.distanceTo( new Point2D( rectHV.xmax(), + (rectHV.ymax() + rectHV.ymin())/2) );
                //assert rectHV.width() == toEdge*2;
                toEdge = (rectHV.xmax() - rectHV.xmin())/2;
            }
            return toEdge > toSplit || (Math.abs(toEdge - toSplit)) < EPSILON;//TODO: remeber how to compare double's equality
        }

        public void draw() {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            point2D.draw();
            if (horizontal) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(0, point2D.y(), 1, point2D.y());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(point2D.x(), 0, point2D.x(), 1);
            }
        }
    }

    private int size = 0;
    private Node root = null;

    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point2D) {
        nullCheck(point2D);
        size++;

        boolean even = true;// -- compare x when the level is even
        if (root == null) {
            root = new Node(point2D, null, null, false);
            return;
        }
        Node tmp = root;
        while (true) {
            double comp = point2D.x();
            double aim = tmp.toComp();
            if (!even) {
                comp = point2D.y();
            }
            even = !even;
            if (comp <= aim) {
                if (point2D.equals(tmp.point2D)) {
                    size--;
                    return;
                }
                if (tmp.left == null) {
                    tmp.left = new Node(point2D, null, null, !even);
                    return;
                }
                tmp = tmp.left;
            } else {
                if (tmp.right == null) {
                    tmp.right = new Node(point2D, null, null, !even);
                    return;
                }
                tmp = tmp.right;
            }
        }

    }

    public boolean contains(Point2D point2D) {
        nullCheck(point2D);

        Node tmp = root;
        boolean even;
        while (tmp != null) {
            even = !tmp.horizontal;
            double comp = point2D.x();
            double aim = tmp.toComp();
            if (!even) {
                comp = point2D.y();
            }
            if (comp < aim) {
                tmp = tmp.left;
            } else if (comp > aim) {
                tmp = tmp.right;
            } else {
                if (point2D.equals(tmp.point2D)){
                    return true;
                }
                tmp = tmp.left;
            }
        }
        return false;
    }

    private void dfs(ArrayList<Node> point2Ds, Node root) {
        if (root == null) {
            return;
        }
        dfs(point2Ds, root.left);
        dfs(point2Ds, root.right);
        point2Ds.add(root);
    }


    public void draw() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        dfs(nodes, root);
        for (Node node : nodes) {
            node.draw();
        }
    }

    public Iterable<Point2D> range(final RectHV rectHV) {
        nullCheck(rectHV);

        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {

                ArrayList<Point2D> res = new ArrayList<Point2D>();
                range(rectHV, root, res);
                return res.iterator();
            }
        };
    }

    /*
    Test 5a: Insert N distinct points and call range() for random query rectangles
  *  4000 random rectangles and 4000 distinct points in 100000-by-100000 grid
  *  4000 random rectangles and 4000 distinct points in 10000-by-10000 grid
     -  student   solution has 429 non-null entries
     -  reference solution has 430 non-null entries
     -  1 missing entry in student solution: (0.371, 0.497)
      -  failed on trial 967 of 4000
      -  rectangle = [0.371, 0.6884] x [0.3527, 0.6964]
  *  4000 random rectangles and 4000 distinct points in 1000-by-1000 grid
     -  student   solution has 56 non-null entries
     -  reference solution has 57 non-null entries
     -  1 missing entry in student solution: (0.204, 0.911)
      -  failed on trial 7 of 4000
      -  rectangle = [0.157, 0.414] x [0.911, 0.971]
  *  4000 random rectangles and 4000 distinct points in 100-by-100 grid
     -  student   solution has 96 non-null entries
     -  reference solution has 99 non-null entries
     -  3 missing entries in student solution, including: (0.16, 0.32)
      -  failed on trial 4 of 4000
      -  rectangle = [0.16, 0.38] x [0.22, 0.34]
  *  4000 random rectangles and 4000 distinct points in 10-by-10 grid
     -  student   solution has 25 non-null entries
     -  reference solution has 30 non-null entries
     -  5 missing entries in student solution, including: (0.4, 0.4)
      -  failed on trial 1 of 4000
      -  rectangle = [0.4, 0.8] x [0.0, 0.5]
  *  4000 random rectangles and 4000 distinct points in 1-by-1 grid
==> FAILED

Test 5b: Insert N points and call range() for random query rectangles
  *  4000 random rectangles and 4000 random points in 10000-by-10000 grid
     -  student   solution has 1611 non-null entries
     -  reference solution has 1612 non-null entries
     -  1 missing entry in student solution: (0.2817, 0.1279)
      -  failed on trial 2969 of 4000
      -  rectangle = [0.2817, 0.8064] x [0.0682, 0.8287]
  *  4000 random rectangles and 4000 random points in 1000-by-1000 grid
     -  student   solution has 355 non-null entries
     -  reference solution has 356 non-null entries
     -  1 missing entry in student solution: (0.194, 0.601)
      -  failed on trial 42 of 4000
      -  rectangle = [0.194, 0.378] x [0.422, 0.886]
  *  4000 random rectangles and 4000 random points in 100-by-100 grid
     -  student   solution has 1466 non-null entries
     -  reference solution has 1501 non-null entries
     -  35 missing entries in student solution, including: (0.22, 0.53)
      -  failed on trial 2 of 4000
      -  rectangle = [0.22, 0.93] x [0.04, 0.68]
  *  4000 random rectangles and 4000 random points in 10-by-10 grid
     -  student   solution has 53 non-null entries
     -  reference solution has 56 non-null entries
     -  3 missing entries in student solution, including: (0.3, 0.6)
      -  failed on trial 2 of 4000
      -  rectangle = [0.3, 1.0] x [0.2, 0.8]
  *  4000 random rectangles and 4000 random points in 1-by-1 grid
==> FAILED
     */
    private void range(RectHV rectHV, Node root, ArrayList<Point2D> res) {
        if (root == null) {
            return;
        }

        if (rectHV.contains(root.point2D)) {
            res.add(root.point2D);
        }
        boolean cross = root.isCross(rectHV);
        //boolean cross = rectHV.intersects(new RectHV(root.point2D.x(), root.point2D.y(), root.point2D.x(), root.point2D.y()));
        if (root.rectInSmall(rectHV)) {
            range(rectHV, root.left, res);
            if (cross) {
                range(rectHV, root.right, res);
            }
        } else {
            range(rectHV, root.right, res);
            if (cross) {
                range(rectHV, root.left,res);
            }
        }
    }


    private Point2D min = null;
    private double minDis = Double.MAX_VALUE;
    private void nearest(Node root, Point2D aim) {
        if (root == null) {
            return;
        }

        double dis = aim.distanceTo(root.point2D);
        if (minDis > dis) {
            min = root.point2D;
            minDis = dis;
        }
        if (root.small(aim)) {
            nearest(root.right, aim);
            double pointToLine = root.disToline(aim);
            if (minDis <= pointToLine) {
                return;
            }
            nearest(root.left, aim);
        } else {
            nearest(root.left, aim);
            double pointToLine = root.disToline(aim);
            if (minDis <= pointToLine) {
                return;
            }
            nearest(root.right, aim);
        }

    }

    public Point2D nearest(Point2D point2D) {
        nullCheck(point2D);

        min = null;
        minDis = Double.MAX_VALUE;
        nearest(root, point2D);
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
