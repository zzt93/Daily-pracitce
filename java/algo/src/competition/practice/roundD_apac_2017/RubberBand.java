package competition.practice.roundD_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by zzt on 11/6/16.
 * <p>
 * <h3></h3>
 */
public class RubberBand implements Comparable<RubberBand> {

    private static RubberBand key = new RubberBand();

    public RubberBand(int i, int i1, int i2) {
        x = i;
        y = i1;
        m = i2;
    }

    public RubberBand() {
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/-small-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        int res;
        for (int i = 0; i < trail; i++) {

            final int n = in.nextInt();
            final int m = in.nextInt();
            final int l = in.nextInt();
            ArrayList<RubberBand> bands = new ArrayList<>(n);
            for (int x = 0; x < n; x++) {
                bands.add(new RubberBand(in.nextInt(), in.nextInt(), in.nextInt()));
            }
            Arrays.fill(used, new TreeSet<Integer>());
            Arrays.fill(money, 0);
            res = getMoney(n, m, l, bands);
            out.println("Case #" + (i + 1) + ": " + (res == 0 ? "IMPOSSIBLE" : res));
        }
    }

    private static int[] money = new int[10001];
    private static TreeSet[] used = new TreeSet[10001];

    private static int getMoney(int n, int m, int l, ArrayList<RubberBand> bands) {
        bands.sort(Comparator.naturalOrder());
        for (int i = 1; i <= l; i++) {
            int min = Integer.MAX_VALUE;
            for (int x = 0; x < i; x++) {
                final TreeSet<Integer> has = used[x];
                int index = Collections.binarySearch(bands, key.setX(l - x));
                if (index < 0) {

                }
                for (int y = 0; y <= index; y++) {
                    final RubberBand band = bands.get(y);
                    //                    if (band.y - x)
                }
            }
            money[l] = min;
        }
        return money[l];
    }

    private int x;
    private int y;
    private int m;

    public RubberBand setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public int compareTo(RubberBand o) {
        return Integer.compare(x, o.x);
    }
}
