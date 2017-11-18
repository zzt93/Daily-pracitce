package interview.leetcode._17x;

/**
 * Created by zzt on 11/16/17.
 * <p>
 * <h3>DP: 'from end to start' is different from 'from start to end'</h3>
 */
public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if (m == 0) {
            return 0;
        }
        int n = dungeon[0].length;
        int[][] health = new int[m][n];
        for (int i = 1; i < n; i++) {
        }
        for (int i = 1; i < m; i++) {
        }
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {

            }
        }
        return health[0][0];
    }

    public static void main(String[] args) {
        DungeonGame d = new DungeonGame();
        System.out.println(d.calculateMinimumHP(new int[][]{new int[]{1,-4,5,-99},new int[]{2,-2,-2,-1}}));
        System.out.println(d.calculateMinimumHP(new int[][]{new int[]{-2, -3, 3}, new int[]{-5,
                -10, 1}, new int[]{10, 30, -5}}));
        System.out.println(d.calculateMinimumHP(new int[][]{new int[]{1, 2, 3}}));
        System.out.println(d.calculateMinimumHP(new int[][]{new int[]{-1, 2, -3}}));
    }
}
