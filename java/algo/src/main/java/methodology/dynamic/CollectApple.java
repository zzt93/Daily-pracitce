package methodology.dynamic;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3>Des</h3>
 * <p>
 * A table composed of N x M cells,
 * each having a certain quantity of apples, is given.
 * You start from the upper-left corner.
 * At each step you can go down or right one cell.
 * Find the maximum number of apples you can collect.
 * </p>
 * <p>
 * <h3>Thought:</h3>
 * <li>take it as a directed graph: choose a max path from all input path</li>
 * <p>
 * <h3>Related:</h3>
 * <li>minimum path problem</li>
 * <li>maximum path problem</li>
 */
public class CollectApple {

    public static int collectApple(int[][] cells) {
        /**
         * precondition:
         */
        final int N = cells.length;
        assert N > 0;
        final int M = cells[0].length;
        for (int[] cell : cells) {
            assert cell.length == M;
        }

        int[][] maxToHere = new int[N][M];
        maxToHere[0][0] = cells[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i > 0 && j > 0) {
                    maxToHere[i][j] = Math.max(
                            maxToHere[i - 1][j] + cells[i][j],
                            maxToHere[i][j - 1] + cells[i][j]);
                } else if (i > 0) {
                    maxToHere[i][j] = maxToHere[i - 1][j] + cells[i][j];
                } else if (j > 0) {
                    maxToHere[i][j] = maxToHere[i][j - 1] + cells[i][j];
                }
            }
        }
        return maxToHere[N - 1][M - 1];
    }

    public static void main(String[] args) {
        Random random = new Random(23);
        final int bound = 7;
        int N = random.nextInt(bound);
        int M = random.nextInt(bound);
        int[][] cells = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cells[i][j] = random.nextInt(bound);
            }
            System.out.println(Arrays.toString(cells[i]));
        }
        System.out.println(collectApple(cells));
    }
}
