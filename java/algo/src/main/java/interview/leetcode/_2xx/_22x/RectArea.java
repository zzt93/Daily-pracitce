package interview.leetcode._2xx._22x;

/**
 * Created by zzt on 12/22/17.
 * <p>
 * <h3></h3>
 */
public class RectArea {

    public int computeArea(int a, int b, int c, int d, int e, int f, int g, int h) {
        int fi = area(a, b, c, d);
        int s = area(e, f, g, h);
        int left = Math.max(a, e);
        int right = Math.min(g, c);
        int bottom = Math.max(f, b);
        int top = Math.min(d, h);
        //If overlap
        int overlap = 0;
        if(right > left && top > bottom)
            overlap = (right - left) * (top - bottom);
        return s + fi - overlap;
    }

    private int area(int a, int b, int c, int d) {
        return (c - a) * (d - b);
    }


    public static void main(String[] args) {
        RectArea r = new RectArea();
        System.out.println(r.computeArea(0, 0, 1, 1, 0, 0, 1, 1));
        System.out.println(r.computeArea(0, 0, 1, 1, 0, 0, 2, 2));
        System.out.println(r.computeArea(0, 0, 1, 1, 1, 1, 2, 2));
        System.out.println(r.computeArea(0, 0, 2, 2, 1, 1, 3, 3));
        System.out.println(r.computeArea(-1, 0, 1, 1, 0, -1, 2, 2));
    }
}
