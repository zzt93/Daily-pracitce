package competition.leetcode.week24;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 3/19/17.
 * <p>
 * <h3></h3>
 */
public class OneZeroMatrix {

    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int rl = matrix.size();
        if (matrix.isEmpty()) {
            return matrix;
        }
        int cl = matrix.get(0).size();
        boolean more = true;
        while (more) {
            more = false;
            for (int r = 0; r < rl; r++) {
                List<Integer> row = matrix.get(r);
                for (int c = 0; c < cl; c++) {
                    Integer col = row.get(c);
                    if (col != 0) {
                        int min = surround(r, c, rl, cl, matrix);
                        if (min + 1 > col) {
                            row.set(c, min + 1);
                            more = true;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private int surround(int r, int c, int rl, int cl, List<List<Integer>> matrix) {
        int min = Integer.MAX_VALUE;
        boolean nl = false, nr = false, nu = false, nd = false;
        if (r == 0) {
            nu = true;
        }
        if (r == rl - 1) {
            nd = true;
        }
        if (c == 0) {
            nl = true;
        }
        if (c == cl - 1) {
            nr = true;
        }

        if (!nl) {
            min = Math.min(min, matrix.get(r).get(c - 1));
        }
        if (!nr) {
            min = Math.min(min, matrix.get(r).get(c + 1));
        }
        if (!nu) {
            min = Math.min(min, matrix.get(r - 1).get(c));
        }
        if (!nd) {
            min = Math.min(min, matrix.get(r + 1).get(c));
        }
        return min;
    }

    public static void main(String[] args) {
        OneZeroMatrix m = new OneZeroMatrix();
        int rl = 10, cl = 10;
        List<List<Integer>> list = new ArrayList<>(rl);
        for (int i = 0; i < rl; i++) {
            ArrayList<Integer> e = new ArrayList<>(cl);
            for (int i1 = 0; i1 < cl; i1++) {
                e.add(Math.random() > 0.2 ? 1 : 0);
            }
            list.add(e);
        }
        m.updateMatrix(list);
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + ",");
            }
            System.out.println();
        }
    }
}
