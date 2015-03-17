import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zzt on 3/1/15.
 */
public class Board {

    private static final char BLANK = 0;
    private byte[][] board;
    private int size ;
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }

        size = blocks.length;
        board = new byte[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = (byte)blocks[i][j];
            }
        }
    }
    private Board(byte[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }

        size = blocks.length;
        board = new byte[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        int count = 0;
        int goal = 1;
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size; i1++) {
                if (goal == size * size) {
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
                if (board[i][j] == BLANK) {
                    continue;
                }
                int x = (board[i][j] - 1)/size;
                int y = (board[i][j] - 1)%size;
                count = count + Math.abs(x - i) + Math.abs(y - j);
            }
        }
        return count;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }


    public Board twin() {
        if (board[0][0] == 0
                || board[0][1] == 0) {
            return twin(new int[]{1, 0}, new int[]{1, 1});
        } else {
            return twin(new int[]{0, 0}, new int[]{0, 1});
        }
    }

    private Board twin(int[] f, int[] s) {
        byte[][] block = new byte[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < size; j++) {
                block[i][j] = board[i][j];
            }
        }
        swap(block, f, s);

        return new Board(block);
    }

    private void swap(byte[][] block, int[] f, int[] s) {
        byte tmp = block[f[0]][f[1]];
        block[f[0]][f[1]] = block[s[0]][s[1]];
        block[s[0]][s[1]] = tmp;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board tmp = (Board) y;
        for (int i = 0; i < size; i++) {
            if (!Arrays.equals(board[i], tmp.board[i])) {
                return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                LinkedList<Board> nei = new LinkedList<Board>();
                int[] position = findBlank();
                int[][] around = around(position[0], position[1]);
                for (int[] ints : around) {
                    nei.push(twin(position, ints));
                }
                return nei.iterator();
            }


        };
    }

    private int[][] around(int i, int j){
        int[] left = {i, j-1};
        int[] right = {i, j+1};
        int[] up = {i-1, j};
        int[] down = {i+1, j};
        int start = 0;
        int end = size - 1;

        if (i == start){
            if (j == start){
                return new int[][]{right, down, };
            } else if (j > start && j < end){
                return new int[][]{left, right, down, };
            } else if (j == end){
                return new int[][]{left, down, };
            }
        } else if (i > start && i < end){
            if (j == start){
                return new int[][]{up, right, down};
            } else if (j > start && j < end){
                return new int[][]{up, right, down, left};
            } else if (j == end){
                return new int[][]{up, left, down};
            }
        } else if (i == end){
            if (j == start) {
                return new int[][]{right, up};
            } else if (j > start && j < end) {
                return new int[][]{up, left, right};
            } else if (j == end){
                return new int[][]{left, up};
            }
        }
        throw new IllegalArgumentException("i is " + i + " j is " + j);
    }

    private int[] findBlank() {
        int[] pos = new int[2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        throw new IllegalArgumentException("board is invalid for no zero in it");
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
