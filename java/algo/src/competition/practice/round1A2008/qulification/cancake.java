package competition.practice.round1A2008.qulification;

import competition.utility.ArrayUtility;
import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zzt on 4/11/15.
 * <p/>
 * Description:
 */
public class cancake {

    public static int cancake(int size, ArrayList<Integer> diners) {
        assert size == diners.size();
        // if all of those are smaller than 3
        Integer max = diners.get(size - 1);
        if (max <= 3) {
            return max;
        }

        int sum = sum(diners);
        double sqrt = Math.sqrt(sum);
        int down = (int) Math.floor(sqrt);
        int up = (int) Math.ceil(sqrt);
        int d_res = split(sum, down, diners);
        int u_res = split(sum, up, diners);
        int half = maxHalf(diners);
        int small = d_res;
        if (d_res > u_res) {
            small = u_res;
        }
        if (max < small) {
            small = max;
        }
        return Math.min(small, half);
    }

    private static int maxHalf(ArrayList<Integer> diners) {
        int half = (int) Math.ceil(diners.get(diners.size() - 1) * 1.0 / 2);
        int count = 0;
        for (Integer diner : diners) {
            if (diner > half) {
                count++;
            }
        }
        return half + count;
    }

    private static int split(int sum, int aim, ArrayList<Integer> diners) {
        int res = aim + (int) Math.ceil(sum * 1.0 / aim) - 1;
        int div = sum / aim;
        int reminder = sum - aim * div;
        int count = 0;
        for (Integer diner : diners) {
            if (diner == aim) {
                res--;
                count++;
                //            } else if (reminder >= diner) {
                //                res--;
                //                count++;
            }
        }
        if (ArrayUtility.binarySearch(diners, 0, diners.size(), reminder) != -1) {
            res--;
            count++;
        }
        if (count == diners.size()) {
            res++;
        }

        return res;
    }

    private static int sum(ArrayList<Integer> diners) {
        int res = 0;
        for (Integer diner : diners) {
            res += diner;
        }
        return res;
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/cancake-small-attempt1.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int size = in.nextInt();
            // ignore '\n'
            in.nextLine();
            ArrayList<Integer> diners = in.oneLineToInt(" ");
            Collections.sort(diners);
            int res = cancake(size, diners);
            out.println("case #" + (i + 1) + ": " + res);
        }
    }
}
