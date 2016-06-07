package competition;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by zzt on 6/7/16.
 * <p>
 * <h3></h3>
 */
@Deprecated
public class Stone {

    public static final int START = -1;

    public static void closestDynamic(LinkedList<Double> doubles, double aim) {
        double[] closestEndHere = new double[doubles.size()];
        int[] index = new int[doubles.size()];
        closestEndHere[0] = doubles.get(0);
        index[0] = START;
        for (int i = 1; i < doubles.size(); i++) {
            double aDouble = doubles.get(i);
            index[i] = START;
            for (int j = 0; j < i; j++) {
                double tmp = closest(aim, aDouble + closestEndHere[j], closestEndHere[i]);
                if (tmp != closestEndHere[i]) {
                    index[i] = j;
                    closestEndHere[i] = tmp;
                }
            }
        }
        double max = 0;
        int j = START;
        for (int i = closestEndHere.length - 1; i >= 0; i--) {
            if (closestEndHere[i] > max) {
                max = closestEndHere[i];
                j = i;
            }
        }
        while (j != START) {
            doubles.remove(doubles.get(j));
            assert j > index[j];
            j = index[j];
        }
    }

    private static double closest(double aim, double newClosestEndHere, double closest) {
        double g1 = closest - aim;
        assert g1 <= 0;
        double g2 = newClosestEndHere - aim;
        if (g1 < g2 && g2 <= 0) {
            return newClosestEndHere;
        }
        return closest;
    }

    public static int stone(ArrayList<Double> stones) {
        Collections.sort(stones);
        final LinkedList<Double> doubles = new LinkedList<>(stones);
        int count = 0;
        while (!doubles.isEmpty()) {
            closestDynamic(doubles, 20);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut();
        try {
            in = new MyIn("testCase/stone-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int res;
        while (in.hasNext()) {
            in.nextLine();
            res = stone(in.oneLineToDouble(" "));
            out.println(res);
        }
    }
}
