package ADT.tree.SegmentTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 */
public class SegmentTree {

    private static class TNode {
        TNode left;
        TNode right;
        List<int[]> ranges = new ArrayList<>();
        int l;
        int r;
    }
}
