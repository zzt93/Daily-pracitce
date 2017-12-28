package interview.leetcode._1xx._13x;

import interview.leetcode.UndirectedGraphNode;

import java.util.HashMap;

/**
 * Created by zzt on 10/16/17.
 * <p>
 * <h3></h3>
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        return clone(node, new HashMap<>());
    }

    private UndirectedGraphNode clone(UndirectedGraphNode now,
                                      HashMap<Integer, UndirectedGraphNode> visited) {
        if (visited.containsKey(now.label)) return visited.get(now.label);

        UndirectedGraphNode clone = new UndirectedGraphNode(now.label);
        visited.put(now.label, clone);
        for (UndirectedGraphNode neighbor : now.neighbors) {
            UndirectedGraphNode node = clone(neighbor, visited);
            clone.neighbors.add(node);
        }
        return clone;
    }
}
