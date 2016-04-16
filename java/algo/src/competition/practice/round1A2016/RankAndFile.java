package competition.practice.round1A2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by zzt on 3/28/15.
 * <p/>
 * CodeJam practice template
 * -- handle input/output and basic argument conversion
 */
public class RankAndFile {


    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/rank-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        ArrayList<Integer> res;
        for (int i = 0; i < trail; i++) {
            int n = in.nextInt();
            ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
            for (int j = 0; j < 2 * n - 1; j++) {
                rows.add(in.oneLineToInt(" "));
            }
            res = findMissing(rows);
            assert res.size() == n;
            out.print("Case #" + (i + 1) + ": ");
            for (Integer re : res) {
                out.print(re + " ");
            }
            out.println();
        }
    }

    private static ArrayList<Integer> findMissing(ArrayList<ArrayList<Integer>> rows) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (ArrayList<Integer> row : rows) {
            for (Integer integer : row) {
                Integer c = counts.get(integer);
                counts.put(integer, (c == null) ? 1 : c + 1);
            }
        }
        for (Integer key : counts.keySet()) {
            if (counts.get(key) % 2 == 1) {
                res.add(key);
            }
        }
        Collections.sort(res);
        return  res;
    }
}
