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

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    public static final int VISITED = -1;

    public int containVirus(int[][] grid) {
        if (grid.length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        boolean[][][] wall = new boolean[m][n][4];
        int last = 0, now = 0;
        do {
            last = now;

            int color = 2, maxCount = 0, maxColor = color;
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    int c = findRegion(grid, m, n, x, y, color++, wall);
                    if (c > maxCount) {
                        maxCount = c;
                        maxColor = color - 1;
                    }
                }
            }

            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == VISITED) {
                        grid[x][y] = 1;
                    }
                }
            }
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == maxColor) {
                        grid[x][y] = 0;
                        if (x > 0 && grid[x - 1][y] == 1) {
                            wall[x][y][UP] = true;
                            now++;
                        }
                        if (x < m - 1 && grid[x + 1][y] == 1) {
                            wall[x][y][DOWN] = true;
                            now++;
                        }
                        if (y > 0 && grid[x][y - 1] == 1) {
                            wall[x][y][LEFT] = true;
                            now++;
                        }
                        if (y < n - 1 && grid[x][y + 1] == 1) {
                            wall[x][y][RIGHT] = true;
                            now++;
                        }
                    }
                }
            }
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] > 1) {
                        grid[x][y] = 1;
                    }
                }
            }
        } while (last != now);
        return now;
    }

    private int findRegion(int[][] grid, int m, int n, int x, int y, int col, boolean[][][] wall) {
        if (grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = VISITED;
        int sum = 0;
        if (x > 0)
            if (grid[x - 1][y] == 1) {
                sum += findRegion(grid, m, n, x - 1, y, col, wall);
            } else if (grid[x - 1][y] == 0 && !wall[x - 1][y][DOWN]) {
                grid[x - 1][y] = col;
                sum++;
            }
        if (x < m - 1)
            if (grid[x + 1][y] == 1) {
                sum += findRegion(grid, m, n, x + 1, y, col, wall);
            } else if (grid[x + 1][y] == 0 && !wall[x + 1][y][UP]) {
                grid[x + 1][y] = col;
                sum++;
            }
        if (y > 0)
            if (grid[x][y - 1] == 1) {
                sum += findRegion(grid, m, n, x, y - 1, col, wall);
            } else if (grid[x][y - 1] == 0 && !wall[x][y - 1][RIGHT]) {
                grid[x][y - 1] = col;
                sum++;
            }

        if (y < n - 1)
            if (grid[x][y + 1] == 1) {
                sum += findRegion(grid, m, n, x, y + 1, col, wall);
            } else if (grid[x][y + 1] == 0 && !wall[x][y + 1][LEFT]) {
                grid[x][y + 1] = col;
                sum++;
            }

        return sum;
    }

    public static void main(String[] args) {
        ContainVirus c = new ContainVirus();
//        System.out.println(c.containVirus(new int[][]{new int[]{0, 1, 0, 0, 0, 0, 0, 1}, new
//                int[]{0, 1, 0, 0, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}}));
//        System.out.println(c.containVirus(new int[][]{}));
        System.out.println(c.containVirus(new int[][]{new int[]{1,1,1,0,0,0,0,0,0},new int[]{1,0,1,0,1,1,1,1,1},new int[]{1,1,1,0,0,0,0,0,0}}));
    }

}
