package competition.leetcode.w67;


/**
 * Created by zzt on 1/14/18.
 * <p>
 * <h3></h3>
 */
public class LargestPlusSign {

    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] grid = new int[N][N];
        for (int[] mine : mines) {
            int x = mine[0];
            int y = mine[1];
            grid[x][y] = -1;
        }
        int[][] left = new int[N][N];
        int[][] up = new int[N][N];
        int[][] down = new int[N][N];
        int[][] right = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (grid[x][y] == 0) {
                    left[x][y] = y == 0 ? 1 : left[x][y - 1] + 1;
                    up[x][y] = x == 0 ? 1 : up[x - 1][y] + 1;
                }
            }
        }
        for (int x = N - 1; x >= 0; x--) {
            for (int y = N - 1; y >= 0; y--) {
                if (grid[x][y] == 0) {
                    right[x][y] = y == N - 1 ? 1 : right[x][y + 1] + 1;
                    down[x][y] = x == N - 1 ? 1 : down[x + 1][y] + 1;
                }
            }
        }
        int max = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                max = Math.max(max, Math.min(Math.min(left[x][y], right[x][y]), Math.min
                        (up[x][y], down[x][y])));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LargestPlusSign largestPlusSign = new LargestPlusSign();
        System.out.println(largestPlusSign.orderOfLargestPlusSign(5, new int[][]{new int[]{4, 2}}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(2, new int[][]{new int[]{0, 0},
                new int[]{1, 1}}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(5, new int[][]{new int[]{3, 0},
                new int[]{3, 3}}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(2, new int[][]{new int[]{0, 0}}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(5, new int[][]{}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(2, new int[][]{}));
        System.out.println(largestPlusSign.orderOfLargestPlusSign(1, new int[][]{new int[]{0, 0}}));
    }
}
