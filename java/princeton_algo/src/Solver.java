import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zzt on 3/1/15.
 */
public class Solver {
    private Node initial;
    private boolean solved;
    private MinPQ<Node> pq;
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        this.initial = new Node(initial, null, 0);
        solved = false;
        pq = new MinPQ<Node>(new Priority());
        simulte();
    }


    private void simulte() {
        Node twin = initial.twin();
        MinPQ<Node> tpq = new MinPQ<Node>(new Priority());
        pq.insert(initial);
        tpq.insert(twin);
        while (!pq.min().isGoal() && !tpq.min().isGoal()) {
            Node min = pq.min();
            Node min1 = tpq.min();
            pq.delMin();
            tpq.delMin();
            for (Node node : min.neighbor()) {
                pq.insert(node);
            }
            for (Node node : min1.neighbor()) {
                tpq.insert(node);
            }
        }
        if (pq.min().isGoal()) {
            solved = true;
        } else {
            solved = false;
        }
    }

    public boolean isSolvable() {
        return solved;
    }

    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return pq.min().moves;
    }

    public Iterable<Board> solution()
    {
        if (!isSolvable()) {
            return null;
        }

        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                LinkedList<Board> linkedList = new LinkedList<Board>();
                Node last = pq.min();
                while (last != null) {
                    linkedList.push(last.now);
                    last = last.pre;
                }
                return linkedList.iterator();
            }
        };
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

    private class Priority implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            int m1 = o1.manhattan();
            int m2 = o2.manhattan();
            int f = o1.moves + m1;
            int s = o2.moves + m2;
            if (f < s) {
                return -1;
            } else if (f > s) {
                return 1;
            } else {
                if (m1 < m2) {
                    return -1;
                } else if (m1 > m2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    private class Node {
        private Board now;
        private Node pre;
        int moves;
        private int count;

        public Node(Board now, Node pre, int moves) {
            this.now = now;
            this.pre = pre;
            this.moves = moves;
            count = now.manhattan();
        }

        public Node twin() {
            return new Node(now.twin(), pre, moves);
        }

        public boolean isGoal() {
            return now.isGoal();
        }

        public Iterable<Node> neighbor() {
               return new Iterable<Node>() {
                   @Override
                   public Iterator<Node> iterator() {
                       Iterable<Board> tmp = now.neighbors();
                       LinkedList<Node> linkedList = new LinkedList<Node>();
                       for (Board board : tmp) {
                           if (pre != null && board.equals(pre.now)) {
                               continue;
                           }
                           linkedList.push(new Node(board, Node.this, moves + 1));
                       }
                       return linkedList.iterator();
                   }
               };
        }

        public int manhattan() {
            return count;
        }
    }
}
