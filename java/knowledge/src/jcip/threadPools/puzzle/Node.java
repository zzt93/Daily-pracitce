package jcip.threadPools.puzzle;

import annotation.Immutable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 5/5/16.
 * <p>
 * Usage:
 */
@Immutable
public class Node<P, M> {
    private final Node<P, M> prev;
    private final M move;
    final P now;

    public Node(Node<P, M> prev, M move, P now) {
        this.prev = prev;
        this.move = move;
        this.now = now;
    }


    public List<M> asMoveList() {
        List<M> res = new LinkedList<>();
        Node<P, M> now = this;
        while (now.move != null) {
            res.add(0, now.move);
            now = now.prev;
        }
        return res;
    }
}
