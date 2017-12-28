package competition.practice.round1a2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by zzt on 4/15/17.
 * <p>
 * <h3></h3>
 */
public class B {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/kit-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            int n = in.nextInt();
            int p = in.nextInt();
            in.nextLine();
            ArrayList<Integer> ingredients = in.oneLineToInt(" ");
            ArrayList<ArrayList<Integer>> packages = new ArrayList<>(n);
            for (int x = 0; x < n; x++) {
                ArrayList<Integer> list = in.oneLineToInt(" ");
                list.sort(Comparator.naturalOrder());
                packages.add(list);
            }
            res = kit(ingredients, packages);
            out.println("Case #" + (i + 1) + ": " + res);
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static long kit(ArrayList<Integer> ingredients, ArrayList<ArrayList<Integer>>
            packages) {
        return 0;
    }

}
