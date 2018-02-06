package interview.leetcode._2xx._28x;

/**
 * @author zzt
 */
public class GameOfLife {

  private static final int DEAD = 0;
  private static final int LIVE = 1;
  private static final int NEXT_DEAD = -1;
  private static final int NEXT_LIVE = 2;

  public void gameOfLife(int[][] board) {
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        int ne = 0;
//        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
//          for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
//
//          }
//        }
        if (x != 0) {
          ne += live(board[x - 1][y]);
          if (y != 0) {
            ne += live(board[x - 1][y - 1]);
          }
          if (y != board[x].length - 1) {
            ne += live(board[x - 1][y + 1]);
          }
        }
        if (x != board.length - 1) {
          ne += live(board[x + 1][y]);
          if (y != 0) {
            ne += live(board[x + 1][y - 1]);
          }
          if (y != board[x].length - 1) {
            ne += live(board[x + 1][y + 1]);
          }
        }
        if (y != 0) {
          ne += live(board[x][y - 1]);
        }
        if (y != board[x].length - 1) {
          ne += live(board[x][y + 1]);
        }

        if (board[x][y] == DEAD && ne == 3) {
          board[x][y] = NEXT_LIVE;
        } else if (board[x][y] == LIVE) {
          if (ne < 2 || ne > 3) {
            board[x][y] = NEXT_DEAD;
          }
        }
      }
    }
    for (int[] row : board) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] == NEXT_DEAD) {
          row[i] = DEAD;
        } else if (row[i] == NEXT_LIVE) {
          row[i] = LIVE;
        }
      }
    }
  }

  private int live(int i) {
    return i == LIVE || i == NEXT_DEAD ? 1 : 0;
  }

}
