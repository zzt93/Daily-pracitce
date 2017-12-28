package competition.leetcode.w53;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by zzt on 10/8/17.
 * <p>
 * <h3></h3>
 */
public class NumDistinctIsland {

    private static final int VISITED = 2;

    private static class Island {
        private int size;
        private int x;
        private int y;
        //        private int leftmost;
        //        private int rightmost;
        //        private int upmost;
        //        private int downmost;
        private int hash;
        private static final Comparator<int[]> temp = Comparator.comparingInt(i -> i[0]);
        private final Comparator<int[]> comparator = temp.thenComparingInt(i -> i[1]);
        private TreeSet<int[]> set = new TreeSet<>(comparator);

        Island(int i, int j) {
            x = i;
            y = j;
        }

        private void add(int x1, int y1) {
            //            upmost = Math.min(x1, x);
            //            downmost = Math.max(x, x1);
            //            leftmost = Math.min(y1, y);
            //            rightmost = Math.max(y1, y);

            size++;
            int xg = x1 - x;
            int yg = y1 - y;
            hash += (xg * 50 + yg);
            set.add(new int[]{xg, yg});
        }

        //        private int cc() {
        //            return rightmost - leftmost;
        //        }

        //        private int rc() {
        //            return downmost - upmost;
        //        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Island island = (Island) o;

            if (size != island.size) return false;
            boolean b = hash == island.hash;
            //                    && rc() == island.rc() && cc() == island.cc();
            if (!b) {
                return false;
            }
            for (Iterator<int[]> it1 = set.iterator(), it2 = island.set.iterator();
                 it1.hasNext();) {
                int[] n1 = it1.next();
                int[] n2 = it2.next();
                if (comparator.compare(n1, n2) != 0) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = size;
            result = 31 * result + hash;
            //            result = 31 * result + rc();
            //            result = 51 * result + cc();
            return result;
        }
    }

    public int numDistinctIslands(int[][] grid) {
        HashSet<Island> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == VISITED || row[j] == 0) {
                    continue;
                }
                Island e = new Island(i, j);
                dfs(i, j, grid, e);
                set.add(e);
            }
        }
        return set.size();
    }

    private void dfs(int i, int j, int[][] grid, Island e) {
        if (grid[i][j] == VISITED || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = VISITED;
        e.add(i, j);
        if (i != 0) {
            dfs(i - 1, j, grid, e);
        }
        if (i != grid.length - 1) {
            dfs(i + 1, j, grid, e);
        }
        if (j != 0) {
            dfs(i, j - 1, grid, e);
        }
        if (j != grid[0].length - 1) {
            dfs(i, j + 1, grid, e);
        }
    }

    public static void main(String[] args) {
        NumDistinctIsland is = new NumDistinctIsland();
        System.out.println(is.numDistinctIslands(new int[][]{{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1}, {1, 1, 0, 0, 0}}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1}, {1, 1, 0, 0, 0}}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1},}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1},}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1},}));
        System.out.println(is.numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1}, {1, 1, 0, 1, 0},}));
    }
}
