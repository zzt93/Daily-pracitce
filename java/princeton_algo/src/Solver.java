/**
 * Created by zzt on 3/1/15.
 */
public class Solver {
    Board initial;
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = initial;
    }

    public boolean isSolvable() {

    }

    public int moves() {

    }

    public Iterable<Board> solution() {

    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
