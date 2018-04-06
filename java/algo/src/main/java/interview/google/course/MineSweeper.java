package interview.google.course;

import java.util.Random;

/**
 * Created by zzt on 3/16/18.
 * <p>
 * <h3></h3>
 */
public class MineSweeper {

    private final int[][] board;

    public MineSweeper(int r, int c, int m) {
        board = new int[r][c];
        init(board, m);
    }


    private void init(int[][] board, int m) {
        // random position
        int select = m, r = board.length, c = board[0].length;
        int n = r * c;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            if (random.nextInt(Integer.MAX_VALUE) % (n-i) < select) {
                setBoard(board, i / c, i % c);
                select--;
            }
        }
    }

    private void setBoard(int[][] board, int r, int c) {
        for (int i = Math.max(0, r - 1); i < Math.min(r + 2, board.length); i++) {
            for (int j = Math.max(0, c - 1); j < Math.min(c + 2, board[0].length); j++) {
                if (i == r && j == c) {
                    board[i][j] = 9;
                } else if (board[i][j] != 9) {
                    board[i][j]++;
                }
            }
        }
    }

    private void print() {
        for (int[] row : board) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new MineSweeper(5, 5, 5).print();
        new MineSweeper(5, 5, 25).print();
    }
}
