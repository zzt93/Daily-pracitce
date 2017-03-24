package jcip.threadPools.puzzle;

import java.util.Set;

/**
 * Created by zzt on 5/5/16.
 * <p>
 * Usage:
 */
public interface Puzzle<P, M> {
    P initPosition();

    boolean isGoal(P position);

    Set<M> legalMove(P position);

    P move(P position, M move);
}
