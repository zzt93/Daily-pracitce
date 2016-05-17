package methodology.dynamic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

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
 * <h3>Thought:</h3>
 * <li>identify the sub-state of this problem</li>
 */
public class ShortestPathWithMoney {

    private static final int MONEY_BOUND = 100;
    private static final int NUM_BOUND = 10;
    private static final int WEIGHT_BOUND = 10;

    public static void main(String[] args) {
        Random r = new Random(13);
        final int numOfV = r.nextInt(NUM_BOUND);
        int[][] graph = new int[numOfV][numOfV];
        int[] money = new int[numOfV];
        for (int i = 0; i < numOfV; i++) {
            graph[i] = new int[numOfV];
            money[i] = r.nextInt(MONEY_BOUND);
            for (int j = 0; j < graph[i].length; j++) {
                if (i == j) {
                    continue;
                }
                // add edge with 25% possibility
                if (r.nextInt() % 4 == 0) {
                    graph[i][j] = r.nextInt(WEIGHT_BOUND);
                }
            }
        }
        shortest(graph, money, r.nextInt(500) + MONEY_BOUND);
    }

    private static ArrayList<Integer> shortest(int[][] graph, int[] money, int sum) {
        // construction
        final int numOfV = graph.length;
        Vertex[] vertices = new Vertex[numOfV];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(money[i]);
        }
        for (int i = 0; i < numOfV; i++) {
            final int[] edges = graph[i];
            for (int j = 0; j < edges.length; j++) {
                final int edge = edges[j];
                if (edge != 0) {
                    vertices[i].addEdge(vertices[j], edge);
                }
            }
        }

        // compute
        PriorityQueue<Edge> nextEdge = new PriorityQueue<>();
        // visit start point
        vertices[0].includeHere(sum, nextEdge, null, null);
        while (!nextEdge.isEmpty()) {
            final Edge min = nextEdge.poll();
            // update other out edge
            for (Edge edge : nextEdge) {
                final Vertex[] fromTo = edge.getFromTo();
                fromTo[0].updateDis(vertices[1], edge);
            }
            // update min one
            final Vertex[] fromTo = min.getFromTo();
            fromTo[0].includeHere(sum, nextEdge, fromTo[1], min);
        }
        ArrayList<Integer> res = new ArrayList<>();
        if (vertices[numOfV - 1].visited()) {

            return res;
        }
        return res;
    }

    private static class Edge implements Comparable<Edge> {
        private int weight;
        private Vertex one;
        private Vertex another;

        public Edge(Vertex vertex, Vertex other, int weight) {
            one = vertex;
            another = other;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull Edge o) {
            return Integer.compare(weight, o.weight);
        }

        public Vertex[] getFromTo() {
            if (one.visited()) {
                assert !another.visited();
                return new Vertex[]{one, another};
            } else {
                assert another.visited();
                return new Vertex[]{another, one};
            }
        }
    }

    private static class Vertex {
        private ArrayList<Edge> edges = new ArrayList<>();

        private int money;
        private int minDis = Integer.MAX_VALUE;
        private int minMoney = Integer.MAX_VALUE;
        private boolean visited = false;

        public Vertex(int i) {
            money = i;
        }

        public void addEdge(Vertex vertex, int weight) {
            edges.add(new Edge(this, vertex, weight));
        }

        public void includeHere(final int sum, PriorityQueue<Edge> nextEdge, Vertex from, Edge by) {
            if (from == null || sum > from.minMoney + money) {
                updateMinDis(from, by);
                updateMinMoney(from);
                nextEdge.addAll(edges);
            }
        }

        private void updateMinMoney(Vertex from) {
            if (from == null) {
                minMoney = money;
            } else {
                minMoney = from.minMoney + money;
            }
        }

        private void updateMinDis(Vertex from, Edge by) {
            if (from == null) { // start vertex
                assert by == null;
                minDis = 0;
            } else {
                minDis = from.minDis + by.weight;
            }
            visited = true;
        }

        public boolean visited() {
            return visited;
        }

        public void updateDis(Vertex vertex, Edge edge) {
            if (minDis < vertex.minDis + edge.weight) {
                minDis = vertex.minDis + edge.weight;
            }
        }
    }
}
