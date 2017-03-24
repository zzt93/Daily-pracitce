package jcip.threadPools.puzzle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage: fix the problem of can't exit if no solution found
 */
public class FixedPuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    private final AtomicLong alive = new AtomicLong();

    public FixedPuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    @Override
    protected void bfs(Node<P, M> now) {
        alive.incrementAndGet();
        try {
            super.bfs(now);
        } finally {
            if (alive.decrementAndGet() == 0) {
                latch.setSolution(null);
            }
        }
    }
}
