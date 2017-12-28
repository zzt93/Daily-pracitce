package interview.leetcode._2xx._20x;

/**
 * Created by zzt on 12/5/17.
 * <p>
 * <h3></h3>
 */
public class NumOfIslands {

    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        for (int x = 0; x < m; x++) {
            char[] row = grid[x];
            for (int y = 0; y < row.length; y++) {
                if (grid[x][y] != '0') {
                    res++;
                    dfs(grid, x, y);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int x, int y) {
        if (grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        if (x != 0) {
            dfs(grid, x - 1, y);
        }
        if (y != 0) {
            dfs(grid, x, y - 1);
        }
        if (x != grid.length - 1) {
            dfs(grid, x + 1, y);
        }
        if (y != grid[0].length - 1) {
            dfs(grid, x, y + 1);
        }
    }

}
