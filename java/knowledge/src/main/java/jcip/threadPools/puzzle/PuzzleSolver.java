package jcip.threadPools.puzzle;

import java.util.List;

/**
 * Created by zzt on 5/5/16.
 * <p>
 * Usage:
 */
public interface PuzzleSolver<P, M> {

    List<M> solve();
}
