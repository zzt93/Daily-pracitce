package competition.leetcode.w63;

/**
 * Created by zzt on 12/17/17.
 * <p>
 * <h3></h3>
 * <ul>
 * <li>how to represent a region? only start</li>
 * <li>how to count region neighbour? de-dup: set a number</li>
 * <li>how to represent a wall? four index -> two index and one direction</li>
 * <li>when to end? new round not affect new cell</li>
 * </ul>
 */
public class ContainVirus {

    private static final int VISITED = -1;

    public int containVirus(int[][] grid) {
        if (grid.length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        boolean[][][] wall = new boolean[m][n][4];
        int[][][] col = new int[m][n][4];
        int last = 0, now = 0;
        do {
            last = now;

            int color = 2, maxCount = 0, maxColor = color;
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] != 1) continue;
                    int c = findRegion(grid, m, n, x, y, col, color++, wall);
                    if (c > maxCount) {
                        maxCount = c;
                        maxColor = color - 1;
                    }
                }
            }

            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == VISITED) { // reset to revisit
                        grid[x][y] = 1;
                    }
                }
            }
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == 1) {
                        for (int d = 0; d < 4; d++) {
                            if (col[x][y][d] == maxColor) {
                                grid[x + dir[d][0]][y + dir[d][1]] = 0;
                                // setup wall
                                wall[x][y][d] = true;
                                now++;
                                // clear to avoid future entrance
                                col[x][y][d] = 0;
                            }
                        }
                    }
                }
            }
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == 1) {
                        for (int d = 0; d < 4; d++) {
                            if (col[x][y][d] != 0 && !wall[x][y][d]) {
                                // infect
                                grid[x + dir[d][0]][y + dir[d][1]] = 1;
                                // clear to avoid future entrance
                                col[x][y][d] = 0;
                            }
                        }
                    }
                }
            }
        } while (last != now);
        return now;
    }

    private int[][] dir = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private int findRegion(int[][] grid, int m, int n, int x, int y, int[][][] colors, int col,
                           boolean[][][] wall) {
        assert grid[x][y] == 1;
        grid[x][y] = VISITED;
        int sum = 0;
        for (int d = 0; d < 4; d++) {
            int tx = x + dir[d][0];
            int ty = y + dir[d][1];
            if (tx >= 0 && tx < m && ty >= 0 && ty < n) {
                if (grid[tx][ty] == 1 && !wall[x][y][d]) {
                    sum += findRegion(grid, m, n, tx, ty, colors, col, wall);
                } else if (grid[tx][ty] != VISITED && !wall[x][y][d]) {
                    if ((grid[tx][ty] >= 0 && grid[tx][ty] < col)) {
                        grid[tx][ty] = col;
                        sum++;
                    }
                    colors[x][y][d] = col;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        ContainVirus c = new ContainVirus();
        System.out.println(c.containVirus(new int[][]{new int[]{0, 1, 0, 0, 0, 0, 0, 1},
                new int[]{0, 1, 0, 0, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0, 1},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(c.containVirus(new int[][]{}));
        System.out.println(c.containVirus(new int[][]{new int[]{1, 1, 1, 0, 0, 0, 0, 0, 0},
                new int[]{1, 0, 1, 0, 1, 1, 1, 1, 1}, new int[]{1, 1, 1, 0, 0, 0, 0, 0, 0}}));
        System.out.println(c.containVirus(new int[][]{new int[]{1, 1, 1}, new int[]{1, 0, 1},
                new int[]{1, 1, 1}}));
        System.out.println(c.containVirus(new int[][]{new int[]{0, 1, 0, 0, 0, 0, 0, 1}, new
                int[]{0, 1, 0, 1, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0, 1}}));
    }

}
