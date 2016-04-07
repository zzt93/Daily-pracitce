package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by zzt on 4/7/16.
 * <p>
 * Usage:
 */
public class MappingTrie<K extends Comparable<K>, V> {

    private class Node implements Comparable<Node> {
        private K key;
        private V value;
        private TreeMap<K, Node> children = new TreeMap<>();

        public Node(K data) {
            this.key = data;
        }

        public Node(K k, V v) {
            key = k;
            value = v;
        }

        public boolean containsKey(K key) {
            return children.containsKey(key);
        }

        public Node getChild(K key) {
            return children.get(key);
        }

        public Node addChild(K key, Node value) {
            return children.put(key, value);
        }

        @Override
        public int compareTo(Node o) {
            return o.key.compareTo(o.key);
        }
    }

    private Node root;
    private K endSymbol;

    public MappingTrie(K root, K endSymbol) {
        this.root = new Node(root);
        this.endSymbol = endSymbol;
    }

    /**
     * Add a serial of data to tree
     *
     * @param data list of data
     */
    public void add(List<K> data, V value) {
        K last = data.get(data.size() - 1);
        if (last.compareTo(endSymbol) != 0) {
            data.add(endSymbol);
        }
        addUnder(root, data, value);
    }

    private void addUnder(Node root, List<K> data, V value) {
        for (K k : data) {
            boolean contains = root.containsKey(k);
            if (contains) {
                root = root.getChild(k);
            } else {
                Node node = new Node(k);
                root.addChild(k, node);
                root = node;
            }
        }
        assert root.key == endSymbol;
        root.value = value;
    }

    public MappingIterator<K, V> getMappingIterator() {
        return new MappingIterator<K, V>() {
            private Node pointer = root;

            @Override
            public V getValue() {
                if (!canExit()) {
                    return null;
                }
                return pointer.getChild(endSymbol).value;
            }

            @Override
            public boolean hasChild(K data) {
                return pointer.containsKey(data);
            }

            @Override
            public boolean canExit() {
                return pointer.containsKey(endSymbol);
            }

            @Override
            public void move(K data) {
                pointer = pointer.getChild(data);
            }
        };
    }
}
