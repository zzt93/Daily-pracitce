import java.util.*;

/**
 * Created by zzt on 3/1/15.
 */
public class Board {

    private int[][] board;
    int size;
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }
        board = blocks;
        size = blocks.length;
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        int count = 0;
        int goal = 1;
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size; i1++) {
                if (goal == board.length * board.length) {
                    break;
                }
                if (goal != board[i][i1]) {
                    count++;
                }
                goal++;
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = board[i][j]/size;
                int y = board[i][j]%size - 1;
                count = count + (x - i) + (y - j);
            }
        }
        return count;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }


    public Board twin() {
        int[][] block = new int[size][size];
        for (int i = 0; i < board.length; i++) {
            block[i] = Arrays.copyOf(board[i], size);
        }
        if (block[0][0] == 0
                || block[0][1] == 0) {
            swap(block, 0, 0);
        } else {
            swap(block, 1, 0);
        }

        return new Board(block);
    }

    private void swap(int[][] block, int i, int j) {
        int tmp = block[i][j];
        block[i][j] = block[i][j + 1];
        block[i][j + 1] = tmp;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board tmp = (Board) y;
        return Arrays.equals(tmp.board, board);
    }

    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                LinkedList<Board> nei = new LinkedList<Board>();
                
            }
        }
    }

    public  String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.append(String.format("%2d ",board[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {

    }
}
