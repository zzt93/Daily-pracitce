package competition.leetcode.w53;

/**
 * Created by zzt on 10/8/17.
 * <p>
 * <h3></h3>
 */
public class MaxAreaOfIsland {

    private static final int VISITED = 2;

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == VISITED || row[j] == 0) {
                    continue;
                }
                int size = dfs(i, j, grid);
                if (size > max) {
                    max = size;
                }
            }
        }
        return max;
    }

    private int dfs(int i, int j, int[][] grid) {
        if (grid[i][j] == 0 || grid[i][j] == VISITED) {
            return 0;
        }
        grid[i][j] = VISITED;
        int res = 1;
        if (i != 0) {
            res += dfs(i-1, j, grid);
        }
        if (i != grid.length-1) {
            res += dfs(i+1, j, grid);
        }
        if (j != 0) {
            res += dfs(i,j-1, grid);
        }
        if (j != grid[0].length-1) {
            res += dfs(i, j+1, grid);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland max = new MaxAreaOfIsland();
        System.out.println(max.maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(max.maxAreaOfIsland(new int[][]{{0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1,
                0, 0}}));
        System.out.println(max.maxAreaOfIsland(new int[][]{{0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1,
                0, 0}, {0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0}}));
        System.out.println(max.maxAreaOfIsland(new int[][]{{0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1,
                0, 0}, {0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 1, 1, 1, 0}}));
    }
}
