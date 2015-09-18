package practice.round2016;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
 * Output
 * <p>
 * For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is
 * either "Yes" or "No", depending on whether the League members mentioned in the input can be split into two groups
 * with neither of the groups containing a troublesome pair.
 */
public class BadHorse {

    public static final int NO_EDGE = 0;
    public static final int EDGE = 1;
    private static Random random = new Random();

    /**
     * This method is <strong>wrong</strong> for different merge orders will
     * give different results
     * <p>
     * merge vertex until only two edge or fail
     *
     * @param graph -- node represent by int index
     *              only the upper half triangle.
     *              <p>
     *              merge two vertices will update `a1 -> x1 ->c`
     *              <p>
     *              <pre>
     *                                        0 ...... a1 ...b
     *                                        0 x      .     .
     *                                        .   \    .     .
     *                                        .     \  .     .
     *                                        a  ..... x1 ....c
     *                                        .        . \   .
     *                                        .        .   \ .
     *                                        b .............x
     *                                        </pre>
     * @return whether it can split into two class
     */
    private static boolean mergeGraph(ArrayList<int[]> graph) {
        int count = 0;
        for (int later = graph.size() - 1; later >= 0; later--) {
            int[] merge_from = graph.get(later);
            // loop over merge_from to find no edge vertex
            for (int target : merge_from) {
                if (target == NO_EDGE) {
                    // merge two vertices
                    final int[] to_merge = graph.get(target);
                    // change the merged target vertex
                    for (int k = 0; k < to_merge.length; k++) {
                        to_merge[k] += merge_from[k];
                    }
                    // change symmetric vertex and edges
                    for (int node = target + 1; node < later; node++) {
                        graph.get(node)[target] += merge_from[node];
                    }
                } else {
                    count++;
                }
                if (count >= 2) {
                    return false;
                }
            }
        }
        return false;
    }

    public static int maxCut(LinkedList<int[]> matrix) {
        int cut = 0;
        for (int i = 0; i < 1000; i++) {
            int tmp = randomMerge(matrix);
            if (cut < tmp) {
                cut = tmp;
            }
        }
        return cut;
    }

    private static int randomMerge(LinkedList<int[]> matrix) {
        while (matrix.size() != 1) {
            if (countAllEdgeVertex(matrix) >= 2) {
                return 0;
            }
            ArrayList<Integer> noEdgesI = new ArrayList<>(0);
            // merge two vertices which no edges between them and delete large one
            // find first one
            int vertex1 = -1;
            while (noEdgesI.size() == 0) {
                vertex1 = random.nextInt(matrix.size());
                final int[] edgeInfo = matrix.get(vertex1);
                noEdgesI = new ArrayList<>(edgeInfo.length);
                for (int i = 0; i < edgeInfo.length; i++) {
                    if (edgeInfo[i] == NO_EDGE) {
                        noEdgesI.add(i);
                    }
                }
            }
            final int vertex2 = random.nextInt(noEdgesI.size());
            // merge
            int to_del = mergeVertices(matrix, vertex1, vertex2);
            matrix.remove(to_del);
        }
        assert matrix.get(0).length == 1 : "not two vertices associated with each other";
        return matrix.get(0)[0];
    }

    private static int mergeVertices(LinkedList<int[]> matrix, int vertex1, int vertex2) {
        int large = Math.max(vertex1, vertex2);
        int small = Math.min(vertex1, vertex2);
        int[] to_merge = matrix.get(small);
        int[] merge_from = matrix.get(large);
        for (int k = 0; k < to_merge.length; k++) {
            to_merge[k] += merge_from[k];
        }
        // change symmetric vertex and edges
        for (int node = small + 1; node < large; node++) {
            matrix.get(node)[small] += merge_from[node];
        }
        return large;
    }

    public static void main(String[] args) {
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/badhorse-small-practice-1.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        int res;
        HashMap<String, Integer> nameToInt;
        int pairs, count;
        LinkedList<int[]> matrix;
        for (int i = 0; i < trail; i++) {
            pairs = in.nextInt();
            nameToInt = new HashMap<>();
            count = 0;
            matrix = new LinkedList<>();
            // handle new line
            in.nextLine();

            for (int j = 0; j < pairs; j++) {
                final String line = in.nextLine();
                String[] two = line.split(" ");
                if (!nameToInt.containsKey(two[0])) {
                    if (!nameToInt.containsKey(two[1])) {
                        nameToInt.put(two[1], count++);
                        matrix.add(new int[count - 1]);
                    }
                    nameToInt.put(two[0], count++);
                    assert count > 1;
                    final int[] nodes = new int[count - 1];
                    // make an edge in new vertex's array
                    nodes[nameToInt.get(two[1])] = EDGE;
                    matrix.add(nodes);
                }
            }
            if (countAllEdgeVertex(matrix) != 2) {
                res = maxCut(matrix);
            } else {
                res = 0;
            }
            out.println("case #" + (i + 1) + ": " + (res == pairs ? "Yes" : "No"));
        }
    }

    private static long countAllEdgeVertex(LinkedList<int[]> matrix) {
        return matrix.stream().filter(ints -> {
            for (int anInt : ints) {
                if (anInt == NO_EDGE) {
                    return false;
                }
            }
            return true;
        }).count();
    }
}
