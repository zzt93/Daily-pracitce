package competition.practice.roundC_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 10/1/16.
 * <p>
 * <h3>graph can be used to represent dependency or more generally, a relationship</h3>
 * <h3>graph can be used to classify different situation</h3>
 */
public class Evaluation {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/evaluation-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        boolean res;
        for (int i = 0; i < trail; i++) {
            final int n = in.nextInt();
            in.nextLine();
            String[] exp = new String[n];
            for (int jj = 0; jj < n; jj++) {
                exp[jj] = in.nextLine();
            }
            res = evaluate(exp);

            out.println("Case #" + (i + 1) + ": " + (res ? "GOOD" : "BAD"));
        }
    }

    private static class Node {

        public static final int VISITED = 2;
        public static final int VISITING = 1;
        private ArrayList<Node> out = new ArrayList<>();
        private boolean reachable = false;
        private int state = 0;

        void setVisited() {
            state = VISITED;
        }

        void setReachable() {
            reachable = true;
        }

        void addOut(Node n) {
            out.add(n);
        }

        boolean isReachable() {
            return reachable;
        }

        boolean isVisited() {
            return state == VISITED;
        }

        ArrayList<Node> getOut() {
            return out;
        }

        void setVisiting() {
            state = VISITING;
        }

        boolean isVisiting() {
            return state == VISITING;
        }
    }

    private static boolean evaluate(String[] exp) {
        HashMap<String, Node> graph = new HashMap<>(exp.length);
        makeGraph(graph, exp);
        visitGraph(graph);
        for (Node node : graph.values()) {
            if (!node.isReachable()) {
                return false;
            }
        }
        return true;
    }

    private static void visitGraph(HashMap<String, Node> graph) {
        for (String s : graph.keySet()) {
            final Node node = graph.get(s);
            dfs(node);
        }
    }

    private static boolean dfs(Node now) {
        if (now.isVisited()) {
            return now.isReachable();
        }
        if (now.isReachable()) {
            now.setVisited();
            return true;
        }
        now.setVisiting();
        for (Node node : now.getOut()) {
            if (node.isVisiting()) {
                continue;
            }
            if (dfs(node)) {
                node.setReachable();
                now.setReachable();
            }
        }
        now.setVisited();
        return now.isReachable();
    }

    private static void makeGraph(HashMap<String, Node> graph, String[] exp) {
        for (String s : exp) {
            final String[] split = s.split("=");
            String var = split[0];
            String params = split[1].substring(split[1].indexOf("(") + 1, split[1].indexOf(")"));
            Node v = getNode(graph, var);
            if (params.isEmpty()) {
                final Node node = getNode(graph, " ");
                node.setReachable();
                v.addOut(node);
            } else {
                for (String p : params.split(",")) {
                    final Node node = getNode(graph, p);
                    v.addOut(node);
                }
            }
        }
    }

    private static Node getNode(HashMap<String, Node> graph, String s) {
        Node v;
        if (graph.containsKey(s)) {
            v = graph.get(s);
        } else {
            v = new Node();
            graph.put(s, v);
        }
        return v;
    }

}
