package interview.leetcode._13x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 10/7/17.
 * <p>
 * <h3></h3>
 */
public class SurroundedRegions {

    private static final char O = 'O';

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int r = board.length;
        int c = board[0].length;
        int[][] visited = new int[r][c];
        List<int[]> aim = new LinkedList<>();
        for (int i = 0; i < c; i++) {
            if (board[0][i] == O) {
                visited[0][i] = 1;
                aim.add(new int[]{0, i});
            }
            if (board[r - 1][i] == O) {
                visited[r - 1][i] = 1;
                aim.add(new int[]{r - 1, i});
            }
        }
        for (int i = 0; i < r; i++) {
            if (board[i][0] == O) {
                visited[i][0] = 1;
                aim.add(new int[]{i, 0});
            }
            if (board[i][c - 1] == O) {
                visited[i][c - 1] = 1;
                aim.add(new int[]{i, c - 1});
            }
        }

        for (int[] ints : aim) {
            dfs(ints, board, visited, r, c);
        }

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == 0) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int[] s, char[][] board, int[][] visited, int r, int c) {
        List<int[]> surround = surround(s, board, r, c);
        for (int[] is : surround) {
            if (visited[is[0]][is[1]] == 1) {
                continue;
            }
            visited[is[0]][is[1]] = 1;
            dfs(is, board, visited, r, c);
        }
    }

    private List<int[]> surround(int[] s, char[][] board, int r, int c) {
        List<int[]> res = new ArrayList<>();
        int x = s[0], y = s[1];
        if (x != 0) {
            if (board[x - 1][y] == O) {
                res.add(new int[]{x - 1, y});
            }
        }
        if (x != r - 1) {
            if (board[x + 1][y] == O) {
                res.add(new int[]{x + 1, y});
            }
        }
        if (y != 0) {
            if (board[x][y - 1] == O) {
                res.add(new int[]{x, y - 1});
            }
        }
        if (y != c - 1) {
            if (board[x][y + 1] == O) {
                res.add(new int[]{x, y + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SurroundedRegions regions = new SurroundedRegions();
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X',
                'O', 'X', 'X'}};
        regions.solve(board);
        System.out.println(Arrays.deepToString(board));

        char[][] board2 = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X',
                'X', 'O', 'X'}};
        regions.solve(board2);
        System.out.println(Arrays.deepToString(board2));

        char[][] board3 = {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X',
                'X', 'O', 'X'}};
        regions.solve(board3);
        System.out.println(Arrays.deepToString(board3));

        char[][] board4 = {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X',
                'X', 'O', 'X'}};
        regions.solve(board4);
        System.out.println(Arrays.deepToString(board4));

        regions.solve(new char[0][0]);
    }
}
