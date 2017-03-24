package jcip.threadPools.puzzle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage:
 */
public class ConcurrentPuzzleSolver<P, M> implements PuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;
    private final ExecutorService service = Executors.newCachedThreadPool();
    private final Map<P, Boolean> seen = new ConcurrentHashMap<>();
    final ValueLatch<Node<P, M>> latch = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public List<M> solve() {
        final P init = puzzle.initPosition();
        service.execute(() -> bfs(new Node<P, M>(null, null, init)));
        final Node<P, M> solution;
        try {
            solution = latch.getSolution();
            return solution.asMoveList();
        } catch (InterruptedException e) {
            return null;
        } finally {
            service.shutdown();
        }
    }

    protected void bfs(Node<P, M> now) {
        final P position = now.now;
        if (seen.putIfAbsent(position, true) != null || latch.isSet()) {
            // visited or solution is found
            return;
        }
        if (puzzle.isGoal(position)) {
            latch.setSolution(now);
        } else {
            for (M m : puzzle.legalMove(position)) {
                final P next = puzzle.move(position, m);
                service.execute(() -> bfs(new Node<P, M>(now, m, next)));
            }
        }
    }
}
