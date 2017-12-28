package ADT.tree.IntervalTree;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 * <a href="https://en.wikipedia.org/wiki/Interval_tree">Interval Tree in wiki</a>
 */
public class IntervalTree {

    private final TNode root;

    private static class TNode {
        private TNode left;
        private TNode right;
        private double center;
        private final Comparator<int[]> first = Comparator.comparingInt(ints -> ints[0]);
        private TreeSet<int[]> sortS = new TreeSet<>(first.thenComparing(ints -> ints[1]));
        private final Comparator<int[]> second = Comparator.comparingInt(ints -> ints[1]);
        private TreeSet<int[]> sortE = new TreeSet<>(second.thenComparing(ints -> ints[0]));

        TNode(int xCenter) {
            center = xCenter;
        }

        void add(int[] range) {
            sortS.add(range);
            sortE.add(range);
        }

        boolean overlap(int x) {
            for (int[] ints : sortS.headSet(new int[]{x, Integer.MAX_VALUE}, true)) {
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
            for (int[] ints : sortS.headSet(new int[]{x, Integer.MAX_VALUE}, true)) {
                if (ints[1] > x) {
                    res.add(ints);
                }
            }
            return res;
        }

        boolean overlap(int s, int e) {
            if (center >=s && center <= e) return true;
            SortedSet<int[]> subSet = sortS.subSet(new int[]{s, Integer.MIN_VALUE}, new int[]{e,
                    Integer.MAX_VALUE});
            if (!subSet.isEmpty()) {
                return true;
            }
            SortedSet<int[]> subSet1 = sortE.subSet(new int[]{Integer.MIN_VALUE, s}, new
                    int[]{Integer.MAX_VALUE, e});
            return !subSet.isEmpty();
        }

        TNode getChild(int s, int e) {
            Preconditions.checkArgument(center < s || center > e);
            return center < s ? left : right;
        }

        public SortedSet<int[]> getOverlap(int s, int e) {
            SortedSet<int[]> subSet = sortS.subSet(new int[]{s, Integer.MIN_VALUE}, new int[]{e,
                    Integer.MAX_VALUE});
            SortedSet<int[]> subSet1 = sortE.subSet(new int[]{Integer.MIN_VALUE, s}, new
                    int[]{Integer.MAX_VALUE, e});
            SortedSet<int[]> res = new TreeSet<>(first.thenComparing(ints -> ints[1]));
            res.addAll(subSet);
            res.addAll(subSet1);
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
        return overlap(s, e, root);
    }

    private boolean overlap(int s, int e, TNode root) {
        if (root == null) {
            return false;
        }
        return root.overlap(s, e) || overlap(s, e, root.getChild(s, e));
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

    public SortedSet<int[]> getOverlap(int s, int e) {
        List<int[]> enclose = getOverlap((s + e) / 2, root);
        // remove duplicate
        SortedSet<int[]> overlap = getOverlap(s, e, root);
        overlap.addAll(enclose);
        return overlap;
    }

    private SortedSet<int[]> getOverlap(int s, int e, TNode root) {
        if (root == null) {
            return Collections.emptySortedSet();
        }
        SortedSet<int[]> overlap = root.getOverlap(s, e);
        if (e > root.center) {
            overlap.addAll(getOverlap(s, e, root.right));
        }
        if (s < root.center) {
            overlap.addAll(getOverlap(s, e, root.left));
        }
        return overlap;
    }

}
