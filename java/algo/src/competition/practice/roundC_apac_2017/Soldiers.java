package competition.practice.roundC_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by zzt on 10/2/16.
 * <p>
 * <h3></h3>
 */
public class Soldiers {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/soldiers-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        boolean res;
        for (int i = 0; i < trail; i++) {

            int n = in.nextInt();
            TreeSet<XView> xViews = new TreeSet<>();
            TreeSet<YView> yViews = new TreeSet<>();
            for (int jj = 0; jj < n; jj++) {
                int x = in.nextInt();
                int y = in.nextInt();
                xViews.add(new XView(x, y));
                yViews.add(new YView(x, y));
            }
            XView xmin = new XView(Integer.MIN_VALUE, Integer.MAX_VALUE);
            YView ymin = new YView(Integer.MAX_VALUE, Integer.MIN_VALUE);
            res = aTravel(xViews, yViews, xmin, ymin);

            out.println("Case #" + (i + 1) + ": " + (res ? "YES" : "NO"));
        }
    }

    private static class XView implements Comparable<XView> {
        private int x;
        private int y;

        XView(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(XView o) {
            if (x == o.x) {
                return Integer.compare(y, o.y);
            }
            return Integer.compare(x, o.x);
        }

        public int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        void setX(int x) {
            this.x = x;
        }

    }

    private static class YView implements Comparable<YView> {
        private int x;
        private int y;

        YView(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        void setY(int y) {
            this.y = y;
        }

        @Override
        public int compareTo(YView o) {
            if (y == o.y) {
                return Integer.compare(x, o.x);
            }
            return Integer.compare(y, o.y);
        }

    }

    private static boolean aTravel(NavigableSet<XView> xViews, NavigableSet<YView> yViews, XView xM, YView yM) {
        final NavigableSet<XView> newXView = xViews.tailSet(xM, false);
        final NavigableSet<YView> newYView = yViews.tailSet(yM, false);
        int oldx = xM.getX();
        int oldy = yM.getY();
        for (XView xView : newXView) {
            xM.setX(Math.max(oldx, xView.getX()));
            yM.setY(Math.max(oldy, xView.getY()));
            if (bTravel(newXView, newYView, xM, yM)) {
                return true;
            }
            xM.setX(oldx);
            yM.setY(oldy);
        }
        for (YView yView : newYView) {
            xM.setX(Math.max(oldx, yView.getX()));
            yM.setY(Math.max(oldy, yView.getY()));
            if (bTravel(newXView, newYView, xM, yM)) {
                return true;
            }
            xM.setX(oldx);
            yM.setY(oldy);
        }
        return false;
    }

    private static boolean bTravel(NavigableSet<XView> xViews, NavigableSet<YView> yViews, XView xM, YView yM) {
        //        for (int i = 0; i < p.length; i++) {
        //            if (visited.contains(i)) {
        //                continue;
        //            }
        //            visited.add(i);
        //            int[] ints = p[i];
        //            if (xM < ints[0] || yM < ints[1]) {
        //                if (!aTravel(p, visited, Math.max(xM, ints[0]), Math.max(yM, ints[1]))) {
        //                    visited.remove(i);
        //                    return false;
        //                }
        //            }
        //            visited.remove(i);
        //        }
        //        return true;
        final NavigableSet<XView> newXView = xViews.tailSet(xM, false);
        final NavigableSet<YView> newYView = yViews.tailSet(yM, false);
        int oldx = xM.getX();
        int oldy = yM.getY();
        for (XView xView : newXView) {
            xM.setX(Math.max(oldx, xView.getX()));
            yM.setY(Math.max(oldy, xView.getY()));
            if (!aTravel(newXView, newYView, xM, yM)) {
                return false;
            }
            xM.setX(oldx);
            yM.setY(oldy);
        }
        for (YView yView : newYView) {
            xM.setX(Math.max(oldx, yView.getX()));
            yM.setY(Math.max(oldy, yView.getY()));
            if (!aTravel(newXView, newYView, xM, yM)) {
                return false;
            }
            xM.setX(oldx);
            yM.setY(oldy);
        }
        return true;
    }


}
