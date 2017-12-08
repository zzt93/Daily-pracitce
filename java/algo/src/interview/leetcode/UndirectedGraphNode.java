package interview.leetcode;

import interview.leetcode._1xx._13x.CloneGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 10/16/17.
 * <p>
 * <h3></h3>
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
