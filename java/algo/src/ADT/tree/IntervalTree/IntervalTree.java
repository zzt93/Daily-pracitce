package ADT.tree.IntervalTree;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 */
public class IntervalTree {

    private final TNode root;

    private static class TNode {
        private TNode left;
        private TNode right;
        private double center;
        private TreeSet<int[]> sortS = new TreeSet<>(Comparator.comparingInt(ints -> ints[0]));
        private TreeSet<int[]> sortE = new TreeSet<>(Comparator.comparingInt(ints -> ints[1]));

        TNode(int xCenter) {
            center = xCenter;
        }

        void add(int[] range) {
            sortS.add(range);
            sortE.add(range);
        }

        boolean overlap(int x) {
            for (int[] ints : sortS.headSet(new int[]{x, 0}, true)) {
                if (ints[1] > x) {
                    return true;
                }
            }
            return false;
        }

        TNode getChild(int x) {
            return x > center ? right : left;
        }

        LinkedList<int[]> getOverlap(int x) {
            LinkedList<int[]> res = new LinkedList<>();
            for (int[] ints : sortS.headSet(new int[]{x, 0}, true)) {
                if (ints[1] > x) {
                    res.add(ints);
                }
            }
            return res;
        }
    }

    public IntervalTree(int[]... ranges) {
        Preconditions.checkArgument(Arrays.stream(ranges).allMatch(ints -> ints[1] > ints[0]));
        this.root = makeTree(Arrays.asList(ranges));
    }

    private TNode makeTree(List<int[]> ranges) {
        if (ranges.isEmpty()) {
            return null;
        }
        int mid = ranges.size() / 2;
        int xCenter = (ranges.get(mid)[0] + ranges.get(mid)[1]) / 2;
        TNode tNode = new TNode(xCenter);
        List<int[]> left = new LinkedList<>();
        List<int[]> right = new LinkedList<>();
        for (int[] r : ranges) {
            if (r[1] < xCenter) {
                left.add(r);
            } else if (r[0] > xCenter) {
                right.add(r);
            } else {
                tNode.add(r);
            }
        }
        tNode.left = makeTree(left);
        tNode.right = makeTree(right);
        return tNode;
    }

    private boolean overlap(int x, TNode root) {
        if (root == null) {
            return false;
        }
        return root.overlap(x) || overlap(x, root.getChild(x));
    }

    public boolean overlap(int x) {
        return overlap(x, root);
    }

    public boolean overlap(int s, int e) {
        return false;
    }

    private List<int[]> getOverlap(int x, TNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<int[]> overlap = root.getOverlap(x);
        overlap.addAll(getOverlap(x, root.getChild(x)));
        return overlap;
    }

    public List<int[]> getOverlap(int x) {
        return getOverlap(x, root);
    }

    public List<int[]> getOverlap(int s, int e) {
        return null;
    }

}
