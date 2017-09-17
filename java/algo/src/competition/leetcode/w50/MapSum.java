package competition.leetcode.w50;

import java.util.HashMap;

/**
 * Created by zzt on 9/17/17.
 * <p>
 * <h3></h3>
 */
public class MapSum {

    private final Node root;
    private final HashMap<String, Integer> map = new HashMap<>();

    private static class Node {
        int sum;
        char c;
        HashMap<Character, Node> children = new HashMap<>();

        public Node(char c) {
            this.c = c;
        }

        public Node() {

        }

        public Node add(char c, int val) {
            Node orDefault = children.getOrDefault(c, new Node(c));
            orDefault.addSum(val);
            children.put(c, orDefault);
            return orDefault;
        }

        private void addSum(int val) {
            sum += val;
        }

        public boolean has(char c) {
            return children.containsKey(c);
        }

        public Node get(char c) {
            return children.get(c);
        }
    }

    public MapSum() {
        this.root = new Node();
    }

    public void insert(String key, int val) {
        int aim = val;
        if (map.containsKey(key)) {
            aim -= map.get(key);
        }
        map.put(key, val);
        Node now = root;
        for (char c : key.toCharArray()) {
            now = now.add(c, aim);
        }
    }

    public int sum(String prefix) {
        Node now = this.root;
        for (char c : prefix.toCharArray()) {
            if (now.has(c)) {
                now = now.get(c);
            } else {
                return 0;
            }
        }
        return now.sum;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
        System.out.println(mapSum.sum("ap["));
        mapSum.insert("cpp", 5);
        System.out.println(mapSum.sum("c"));
        mapSum.insert("aan", 5);
        System.out.println(mapSum.sum("a"));
        mapSum.insert("aan", 3);
        System.out.println(mapSum.sum("a"));
    }
}
