package competition.practice.round1c2017;

import competition.utility.MyIn;
import competition.utility.MyOut;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 4/30/17.
 * <p>
 * <h3></h3>
 */
public class A {

    private static class Cake implements Comparable<Cake> {
        private int r;
        private int h;

        public Cake(int r, int h) {
            this.r = r;
            this.h = h;
        }

        @Override
        public int compareTo(@NotNull A.Cake o) {
            return Double.compare(o.getRH() + o.getSquare(), getRH() + getSquare());
        }

        public double getRH() {
            return 2 * Math.PI * r * h;
        }

        public double getSquare() {
            return Math.PI * r * r;
        }

        public int getR2() {
            return r * r;
        }
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/ample-small-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        double res;
        for (int i = 0; i < trail; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            Cake max = new Cake(0, 0);
            PriorityQueue<Cake> cakes2 = new PriorityQueue<>(Comparator.comparingDouble
                    (Cake::getRH).reversed());
            PriorityQueue<Cake> cakes3 = new PriorityQueue<>(Comparator.comparingInt(Cake::getR2)
                    .reversed());
            for (int x = 0; x < n; x++) {
                int r = in.nextInt();
                int h = in.nextInt();
                Cake tmp = new Cake(r, h);
                if (max.compareTo(tmp) > 0) {
                    max = tmp;
                }
                cakes2.add(tmp);
                cakes3.add(tmp);
            }
            res = calc(max, cakes2, cakes3, k);
            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static double calc(Cake max, PriorityQueue<Cake> rh, PriorityQueue<Cake> r2, int k) {
        double res = max.getRH() + max.getSquare();
        rh.remove(max);
        r2.remove(max);
        int rMax = max.getR2();
        for (int i = 1; i < k; i++) {
            Cake poll = rh.poll();
            Cake larger = r2.peek();
            Cake remove;
            double square = rMax * Math.PI;
            if (larger.getR2() > rMax) {
                if (larger.getSquare() - square > poll.getRH() - larger.getRH()) {
                    remove = larger;
                    res += (larger.getSquare() + larger.getRH() - square);
                    rMax = larger.getR2();
                } else {
                    remove = poll;
                    res += poll.getRH();
                }
            } else {
                remove = poll;
                res += poll.getRH();
            }
            rh.remove(remove);
            r2.remove(remove);
        }
        return res;
    }
}
