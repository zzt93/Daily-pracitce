package competition.practice.round2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by zzt on 9/16/15.
 * <p>
 * Description: @see
 * <a href="//code.google.com/codejam/contest/dashboard?c=6234486#s=p0">Bad horse</a>
 * <p>
 * Input
 * <p>
 * The first line of the input gives the number of test cases, T.
 * T test cases follow.
 * Each test case starts with a positive integer M on a line by itself
 * -- the number of troublesome pairs of League members.
 * The next M lines each contain a pair of names, separated by a single space.
 * <br>
 * Output
 * <br>
 * For each test case, output one line containing "Case #x: y", where x is the case number (starting
 * from 1) and y is
 * either "Yes" or "No", depending on whether the League members mentioned in the input can be split
 * into two groups
 * with neither of the groups containing a troublesome pair.
 * <p>
 * <p></p>
 * <strong>Better algo: coloring this graph</strong>
 * This algorithm can work for small input, but takes too long for
 * large input and may fail in large input.
 */
public class BadHorse {

    public static final int NO_EDGE = 0;
    public static final int EDGE = 1;
    public static final int TRIANGLE = 3;
    private static Random random = new Random(47);

    //    /**
    //     * This method is <strong>wrong</strong> for different merge orders will
    //     * give different results
    //     * <p>
    //     * merge vertex until only two edge or fail
    //     *
    //     * @param graph -- node represent by int index
    //     *              only the upper half triangle.
    //     *              <p>
    //     *              merge two vertices will update `a1 -> x1 ->c`
    //     *              <p>
    //     *              <pre>
    //     *
    //       0 ...... a1 ...b
    //     *
    //       0 x      .     .
    //     *
    //       .   \    .     .
    //     *
    //       .     \  .     .
    //     *
    //       a  ..... x1 ....c
    //     *
    //       .        . \   .
    //     *
    //       .        .   \ .
    //     *
    //       b .............x
    //     *
    //       </pre>
    //     * @return whether it can split into two class
    //     */
    //    private static boolean mergeGraph(ArrayList<int[]> graph) {
    //        int count = 0;
    //        for (int later = graph.size() - 1; later >= 0; later--) {
    //            int[] merge_from = graph.get(later);
    //            // loop over merge_from to find no edge vertex
    //            for (int target : merge_from) {
    //                if (target == NO_EDGE) {
    //                    // merge two vertices
    //                    final int[] to_merge = graph.get(target);
    //                    // change the merged target vertex
    //                    for (int k = 0; k < to_merge.length; k++) {
    //                        to_merge[k] += merge_from[k];
    //                    }
    //                    // change symmetric vertex and edges
    //                    for (int node = target + 1; node < later; node++) {
    //                        graph.get(node)[target] += merge_from[node];
    //                    }
    //                } else {
    //                    count++;
    //                }
    //                if (count >= 2) {
    //                    return false;
    //                }
    //            }
    //        }
    //        return false;
    //    }

    public static int maxCut(LinkedList<FixedContainer<Integer>> matrix) {
        int cut = 0;
        final int size = matrix.size();
        for (int i = 0; i < size * size * Math.log(size); i++) {
            final LinkedList<FixedContainer<Integer>> matrixDup = deepDupMatrix(matrix);
            int tmp = randomMerge(matrixDup);
            if (cut < tmp) {
                cut = tmp;
            }
        }
        return cut;
    }

    private static LinkedList<FixedContainer<Integer>> deepDupMatrix
            (LinkedList<FixedContainer<Integer>> matrix) {
        LinkedList<FixedContainer<Integer>> res = new LinkedList<>();
        for (FixedContainer<Integer> integerFixedContainer : matrix) {
            res.add(new FixedContainer<>(integerFixedContainer));
        }
        return res;
    }

    private static int randomMerge(LinkedList<FixedContainer<Integer>> matrix) {
        while (matrix.size() != 2) {
            if (countAllEdgedVertex(matrix) >= TRIANGLE) {
                return 0;
            }
            ArrayList<Integer> noEdgesI = new ArrayList<>(0);
            // merge two vertices which no edges between them and delete large one
            // find first one
            int vertex1 = -1;
            while (noEdgesI.size() == 0) {
                vertex1 = random.nextInt(matrix.size());
                final FixedContainer<Integer> edgeInfo = matrix.get(vertex1);
                noEdgesI = new ArrayList<>(edgeInfo.size());
                for (int i = 0; i < edgeInfo.size(); i++) {
                    if (edgeInfo.get(i) == NO_EDGE) {
                        noEdgesI.add(i);
                    }
                }
            }
            final int vertex2 = noEdgesI.get(random.nextInt(noEdgesI.size()));
            // merge
            int to_del = mergeVertices(matrix, vertex1, vertex2);
            matrix.remove(to_del);
        }
        assert matrix.get(1).size() == 1 : "not two vertices associated with each other";
        return matrix.get(1).get(0);
    }

    private static int mergeVertices(LinkedList<FixedContainer<Integer>> matrix, int vertex1, int
            vertex2) {
        int large = Math.max(vertex1, vertex2);
        int small = Math.min(vertex1, vertex2);
        FixedContainer<Integer> to_merge = matrix.get(small);
        FixedContainer<Integer> merge_from = matrix.get(large);
        assert to_merge.size() == small && merge_from.size() == large;
        for (int k = 0; k < small; k++) {
            to_merge.set(k, to_merge.get(k) + merge_from.get(k));
        }
        // change symmetric vertex and edges in the matrix
        for (int node = small + 1; node < large; node++) {
            final FixedContainer<Integer> horizontal = matrix.get(node);
            horizontal.set(small, horizontal.get(small) + merge_from.get(node));
        }
        for (int node = large + 1; node < matrix.size(); node++) {
            final FixedContainer<Integer> horizontal = matrix.get(node);
            horizontal.set(small, horizontal.get(small) + horizontal.get(large));
            horizontal.remove(large);
        }
        return large;
    }

    public static void main(String[] args) {
        //        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/badhorse-small-practice-2.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        int res;
        HashMap<String, Integer> nameToInt;
        int pairs, count;
        LinkedList<FixedContainer<Integer>> matrix;
        for (int i = 0; i < trail; i++) {
            pairs = in.nextInt();
            nameToInt = new HashMap<>(2 * pairs);
            count = 0;
            matrix = new LinkedList<>();
            // handle new line after next int
            in.nextLine();

            for (int j = 0; j < pairs; j++) {
                final String line = in.nextLine();
                String[] two = line.split(" ");
                if (!nameToInt.containsKey(two[0])) {
                    if (!nameToInt.containsKey(two[1])) {
                        nameToInt.put(two[1], count++);
                        matrix.add(new FixedContainer<>(count - 1, 0));
                    }
                    nameToInt.put(two[0], count++);
                    assert count > 1;
                    final FixedContainer<Integer> nodes = new FixedContainer<>(count - 1, 0);
                    // make an edge in new vertex's array
                    nodes.set(nameToInt.get(two[1]), EDGE);
                    matrix.add(nodes);
                } else {
                    FixedContainer<Integer> later;
                    if (!nameToInt.containsKey(two[1])) {
                        nameToInt.put(two[1], count++);
                        later = new FixedContainer<>(count - 1, 0);
                        later.set(nameToInt.get(two[0]), EDGE);
                        matrix.add(later);
                    } else { // two name are all registered
                        final int i0 = nameToInt.get(two[0]);
                        final int i1 = nameToInt.get(two[1]);
                        final int large = Math.max(i0, i1);
                        final int small = Math.min(i0, i1);
                        later = matrix.get(large);
                        later.set(small, EDGE);
                        matrix.set(large, later);
                    }
                }
            }

            if (countAllEdgedVertex(matrix) <= TRIANGLE) {
                res = maxCut(matrix);
            } else {
                res = 0;
            }
            out.println("Case #" + (i + 1) + ": " + (res == pairs ? "Yes" : "No"));
        }
    }

    /**
     * count number of vertices which have edges to all other vertices
     *
     * @param matrix -- half of vertices
     *
     * @return -- the number
     */
    private static long countAllEdgedVertex(LinkedList<FixedContainer<Integer>> matrix) {
        return matrix.stream().
                filter(
                        ints -> ints.stream().allMatch(num -> num >= EDGE)
                ).count();
    }
}
