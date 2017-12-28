package ADT.tree.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by zzt on 1/26/16.
 * <p>
 * Usage:
 * Only working as prefix searching;
 * can't working as a mapping from sequences to something else now
 */
public class PrefixTree<T extends Comparable<T>> {

    private Node root;
    private T endSymbol;

    public PrefixTree(T root, T endSymbol) {
        this.root = new Node(root);
        this.endSymbol = endSymbol;
    }

    /**
     * Add a serial of data to tree
     *
     * @param data list of data
     */
    public void add(ArrayList<T> data) {
        T last = data.get(data.size() - 1);
        if (last.compareTo(endSymbol) != 0) {
            data.add(endSymbol);
        }
        addUnder(root, data);
    }

    private void addUnder(Node root, ArrayList<T> data) {
        for (T t : data) {
            boolean contains = root.containsKey(t);
            if (contains) {
                root = root.get(t);
            } else {
                Node node = new Node(t);
                root.put(t, node);
                root = node;
            }
        }
    }


    public TreeIterator<T> iterator() {
        return new TreeIterator<T>() {
            private Node pointer = root;

            @Override
            public boolean hasChild(T data) {
                return pointer.children.containsKey(data);
            }

            @Override
            public boolean canExit() {
                return pointer.children.containsKey(endSymbol);
            }

            @Override
            public void move(T data) {
                pointer = pointer.children.get(data);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PrefixTree\n");
        //        dfsView(root, stringBuilder);
        bfsView(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void bfsView(Node pointer, StringBuilder stringBuilder) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(pointer);
        Node newLineNode = null;
        queue.add(newLineNode);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (queue.isEmpty()) {
                break;
            }
            if (poll == null) {
                stringBuilder.append('\n');
                queue.add(newLineNode);
            } else {
                stringBuilder.append(poll.data).append(' ');
                poll.children.values().forEach(queue::add);
            }
        }
    }

    private void dfsView(Node pointer, StringBuilder stringBuilder) {
        stringBuilder.append(pointer.data).append(" ");
        for (Node node : pointer.children.values()) {
            dfsView(node, stringBuilder);
        }
        stringBuilder.append("\n");
    }

    class Node implements Comparable<Node> {
        private T data;
        private TreeMap<T, Node> children = new TreeMap<>();

        public Node(T data) {
            this.data = data;
        }

        public boolean containsKey(T key) {
            return children.containsKey(key);
        }

        public Node get(T key) {
            return children.get(key);
        }

        public Node put(T key, Node value) {
            return children.put(key, value);
        }

        @Override
        public int compareTo(Node o) {
            return o.data.compareTo(o.data);
        }
    }
}
