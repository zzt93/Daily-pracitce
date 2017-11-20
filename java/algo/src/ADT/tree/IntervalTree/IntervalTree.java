package ADT.tree.IntervalTree;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 */
public class IntervalTree {

    private static class TNode {
        TNode left;
        TNode right;
        double center;
        TreeSet<int[]> x = new TreeSet<>(Comparator.comparingInt(is->is[0]));
        TreeSet<int[]> y = new TreeSet<>(Comparator.comparingInt(is->is[1]));
    }

    public IntervalTree(int[] ...ranges) {

    }

    public boolean contain(int x) {
        return false;
    }

    public boolean contain(int s, int e) {
        return false;
    }


}
