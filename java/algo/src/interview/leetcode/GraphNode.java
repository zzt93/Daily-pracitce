package interview.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by zzt on 9/21/17.
 * <p>
 * <h3></h3>
 */
public class GraphNode<T> {

    public T t;
    private final List<GraphNode<T>> neighbour = new LinkedList<>();

    public GraphNode(T t) {
        this.t = t;
    }

    public GraphNode<T> addNode(GraphNode<T> node) {
        neighbour.add(node);
        return this;
    }

    public List<GraphNode<T>> getNeighbour() {
        return neighbour;
    }
}
