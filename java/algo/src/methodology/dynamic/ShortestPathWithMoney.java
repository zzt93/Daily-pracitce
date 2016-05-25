package methodology.dynamic;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3>Des</h3>
 * <p>
 * Given an undirected graph G having positive weights and N vertices.
 * You start with having a sum of M money.
 * For passing through a vertex i, you must pay S[i] money.
 * If you don’t have enough money – you can’t pass through that vertex.
 * Find the shortest path from vertex 1 to vertex N, respecting the above conditions;
 * or state that such path doesn’t exist.
 * If there exist more than one path having the same length,
 * then output the cheapest one.
 * </p>
 * Restrictions: 1< N<=100 ; 0<=M<=100 ; for each i, 0<=S[i]<=100.
 * <p>
 * <h3>Critical Thought:</h3>
 * <li>identify the sub-state of this problem: shortest sub-graph</li>
 * <p>
 * <h3>More</h3>
 * <p>Verification of single source (positive edge) shortest path algo</p>
 * <p>(1) at the start point, choose the shortest out edge, we find the shortest path
 * from source to node0. so we include node0 in determined set</p>
 * <p>(2) now, source ... node1 -- node2, is a short. First,
 * distance from source to any node in determined set are shorter than to nodes outside
 * determined set. Second,
 * <li>another out edge can directly reach node1</li></p>
 */
public class ShortestPathWithMoney {

    private static final int MONEY_BOUND = 100;
    private static final int NUM_BOUND = 15;
    private static final int WEIGHT_BOUND = 10;
    private static final int NO_EDGE = -1;
    private static final Vertex MIN_VERTEX = new Vertex(0, Integer.MAX_VALUE);
    private static final Edge MIN_EDGE = new Edge(null, null, 0);

    public static void main(String[] args) {
        Random r = new Random(13);
        final int numOfV = r.nextInt(NUM_BOUND);
        int[][] graph = new int[numOfV][];
        int[] money = new int[numOfV];
        for (int i = 0; i < numOfV; i++) {
            graph[i] = new int[i];
            money[i] = r.nextInt(MONEY_BOUND);
            for (int j = 0; j < graph[i].length; j++) {
                // add edge with 50% possibility
                if (r.nextInt() % 2 == 0) {
                    graph[i][j] = r.nextInt(WEIGHT_BOUND);
                } else {
                    graph[i][j] = NO_EDGE;
                }
            }
            System.out.println(Arrays.toString(graph[i]));
        }
        System.out.println(Arrays.toString(money));
        final int sum = r.nextInt(500) + MONEY_BOUND;
        System.out.println(sum);
        System.out.println(shortest(graph, money, sum));
    }

    private static ArrayList<String> shortest(int[][] graph, int[] money, int sum) {
        // construction of graph
        final int numOfV = graph.length;
        Vertex[] vertices = new Vertex[numOfV];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(money[i], "" + i);
        }
        for (int i = 0; i < numOfV; i++) {
            final int[] edges = graph[i];
            for (int j = 0; j < edges.length; j++) {
                final int edge = edges[j];
                if (edge != NO_EDGE) {
                    vertices[i].addEdge(vertices[j], edge);
                }
            }
        }

        ArrayList<String> res = new ArrayList<>();
        // compute
        PriorityQueue<Edge> nowOutEdges = new PriorityQueue<>();
        // visit start point
        if (vertices[0].tryIncludeThis(Vertex.vertexBeforeStart, Edge.edgeBeforeStart, sum, nowOutEdges)) {
            res.add("0");
        }
        while (!nowOutEdges.isEmpty()) {
            Vertex min = MIN_VERTEX;
            Edge minBy = MIN_EDGE;

            // update other out edge
            for (Iterator<Edge> it = nowOutEdges.iterator(); it.hasNext(); ) {
                final Edge edge = it.next();
                if (edge.visited()) {
                    it.remove();
                    continue;
                }
                final Vertex[] fromTo = edge.getFromTo();
                fromTo[1].updateDisMoney(fromTo[0], edge);
                if (min.further(fromTo[1])) {
                    min = fromTo[1];
                    minBy = edge;
                }
            }
            // update min one
            final Vertex[] fromTo = minBy.getFromTo();
            assert fromTo[1] == min;
            // remove the min edge no matter if it can use
            nowOutEdges.remove(minBy);
            if (min.tryIncludeThis(fromTo[0], minBy, sum, nowOutEdges)) {
                res.add(min.name);
                if (min == vertices[numOfV - 1]) { // meet the target
                    break;
                }
            }
        }
        return res;
    }

    private static class Edge implements Comparable<Edge> {
        private static Edge edgeBeforeStart = new Edge(Vertex.vertexBeforeStart, null, 0);

        private int len;
        private Vertex one;
        private Vertex another;

        Edge(Vertex vertex, Vertex other, int len) {
            one = vertex;
            another = other;
            this.len = len;
        }

        @Override
        public int compareTo(@NotNull Edge o) {
            return Integer.compare(len, o.len);
        }

        Vertex[] getFromTo() {
            if (one.visited()) {
                assert !another.visited();
                return new Vertex[]{one, another};
            } else {
                assert another.visited();
                return new Vertex[]{another, one};
            }
        }

        boolean visited() {
            return one.visited() && another.visited();
        }
    }

    private static class Vertex {
        private static Vertex vertexBeforeStart = new Vertex(0, 0);

        private ArrayList<Edge> edges = new ArrayList<>();

        private int money;
        private int minDis = Integer.MAX_VALUE;
        private int minMoney = Integer.MAX_VALUE;
        private boolean visited = false;
        String name;

        Vertex(int i) {
            money = i;
        }

        private Vertex(int money, int dis) {
            this.minMoney = money;
            this.minDis = dis;
        }

        Vertex(int money, String name) {
            this(money);
            this.name = name;
        }

        void addEdge(Vertex vertex, int weight) {
            final Edge e = new Edge(this, vertex, weight);
            edges.add(e);
            vertex.edges.add(e);
        }

        boolean tryIncludeThis(Vertex from, Edge by, final int limit, PriorityQueue<Edge> nowOutEdges) {
            if (limit > from.minMoney + money) {
                updateMinDis(from, by);
                updateMinMoney(from);
                nowOutEdges.addAll(edges);
                return true;
            }
            return false;
        }

        private void updateMinMoney(Vertex from) {
            assert from != null;
            minMoney = from.minMoney + money;
        }

        private void updateMinDis(Vertex from, Edge by) {
            assert from != null && by != null;
            minDis = from.minDis + by.len;
            visited = true;
        }

        boolean visited() {
            return visited;
        }

        void updateDisMoney(Vertex from, Edge by) {
            if (minDis > from.minDis + by.len) {
                minDis = from.minDis + by.len;
            }
            if (minMoney > from.minMoney + money) {
                minMoney = from.minMoney + money;
            }
        }

        boolean further(Vertex vertex) {
            assert vertex.minDis != Integer.MAX_VALUE;
            return minDis > vertex.minDis
                    || (minDis == vertex.minDis && minMoney > vertex.minMoney);
        }
    }
}
