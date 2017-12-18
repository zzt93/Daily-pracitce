package competition.leetcode.w63;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zzt on 12/17/17.
 * <p>
 * <h3>find a line segment in row, then try to find same segment in another row</h3>
 */
public class CornerRect {

    public int countCornerRectangles(int[][] grid) {
        ArrayList<Integer>[] horizontal = new ArrayList[grid.length];
        ArrayList<Integer>[] ver = new ArrayList[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            horizontal[i] = new ArrayList<>(200);
            int[] row = grid[i];
            for (int x = 0; x < row.length; x++) {
                if (row[x] == 1) {
                    horizontal[i].add(x);
                }
            }
        }
        for (int c = 0; c < grid[0].length; c++) {
            ver[c] = new ArrayList<>(200);
            for (int r = 0; r < grid.length; r++) {
                if (grid[r][c] == 1) {
                    ver[c].add(r);
                }
            }
        }

        int res = 0;
        for (int r = 0; r < horizontal.length; r++) {
            ArrayList<Integer> ints = horizontal[r];
            if (ints.isEmpty()) {
                continue;
            }
            for (int i = 0; i < ints.size(); i++) {
                Integer c = ints.get(i);
                for (int j = i + 1; j < ints.size(); j++) {
                    Integer c2 = ints.get(j);
                    int r2i = Collections.binarySearch(ver[c], r);
                    if (r2i < 0) {
                        r2i = -r2i - 1;
                    } else {
                        r2i++;
                    }
                    for (int k = r2i; k < ver[c].size(); k++) {
                        Integer r2 = ver[c].get(k);
                        if (grid[r2][c2] == 1) res++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CornerRect c = new CornerRect();
        System.out.println(c.countCornerRectangles(new int[][]{new int[]{1, 1, 1},
                new int[]{1, 1, 1},
                new int[]{1, 1, 1}}));
        System.out.println(c.countCornerRectangles(new int[][]{new int[]{1, 0, 0, 1, 0},
                new int[]{0, 0, 1, 0, 1},
                new int[]{0, 0, 0, 1, 0},
                new int[]{1, 0, 1, 0, 1}}));
        System.out.println(c.countCornerRectangles(new int[][]{new int[]{1, 1, 1, 1}}));
    }
}
