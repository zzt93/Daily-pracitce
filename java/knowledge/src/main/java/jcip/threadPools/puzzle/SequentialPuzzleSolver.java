package jcip.threadPools.puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzt on 5/5/16.
 * <p>
 * Usage:
 */
public class SequentialPuzzleSolver<P, M> implements PuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> visited = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public List<M> solve() {
        final P init = puzzle.initPosition();
        return dfsSearch(new Node<P, M>(null, null, init));
    }

    private List<M> dfsSearch(Node<P, M> node) {
        final P now = node.now;
        if (!visited.contains(now)) {
            visited.add(now);
            if (puzzle.isGoal(now)) {
                return node.asMoveList();
            }
            for (M m : puzzle.legalMove(now)) {
                final P next = puzzle.move(now, m);
                final List<M> list = dfsSearch(new Node<P, M>(node, m, next));
                if (list != null) {
                    return list;
                }
            }
        }
        return null;
    }
}
