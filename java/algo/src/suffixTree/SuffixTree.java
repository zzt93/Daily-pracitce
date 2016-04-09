package suffixTree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 * A single string's suffix tree;
 * A set of string's generalized suffix tree(at most 3 string)
 */
public class SuffixTree {


    private int nowIndex;

    private void setCurrent(String current) {
        this.current = current;
    }

    private enum CharNotInAlphabet {
        SHARP("#"), DOLOR("$"), PERCENT("%"),;

        private final String des;

        CharNotInAlphabet(String s) {
            des = s;
        }

        public String getDes() {
            return des;
        }
    }

    private int count = 0;
    private String current;
    private Node root = new Node(0, "");

    private class Node {
        // used in the construction phase
        private int startIndex;
        // used in look up phase
        private String str;
        private Node suffixLink = root;

        private TreeMap<Character, Node> children = new TreeMap<>();

        Node(int startIndex) {
            this.startIndex = startIndex;
        }

        Node(int i, String s) {
            str = s;
            startIndex = i;
        }

        Collection<Node> getChildren() {
            return children.values();
        }

        Node findOrSplitNode(char edge, int activeLen) {
            Node node = children.get(edge);
            while (node.edgeLength() <= activeLen) {// observation two
                activeLen -= node.edgeLength();
                if (activeLen == 0) {
                    break;
                }
                node = node.getChild(current.charAt(nowIndex - activeLen));
                assert node != null;
            }
            if (activeLen != 0) {// find the node, no need to split
                int endIndex = activeLen + node.startIndex;
                // split node by setting endIndex and init string
                node.initString(endIndex);
                // add the rest of splitting node
                char newSon = current.charAt(endIndex);
                node.addChild(newSon, new Node(endIndex));
            }
            return node;
        }

        private Node getChild(char c) {
            return children.get(c);
        }

        private int edgeLength() {
            if (str == null) {
                return nowIndex - startIndex;
            }
            return str.length();
        }

        void addChild(char newSon, Node node) {
            children.put(newSon, node);
        }

        private String initString(int endIndex) {
            str = current.substring(startIndex, endIndex);
            return str;
        }

        boolean containsChild(char c) {
            return children.containsKey(c);
        }

        void setLink(Node n) {
            suffixLink = n;
        }

        Node getSuffixLink() {
            return suffixLink;
        }

        boolean isLeaf() {
            return str == null;
        }

        public String getStr() {
            return str;
        }
    }

    public SuffixTree add(String s) {
        if (count >= CharNotInAlphabet.values().length) {
            System.err.println("can't add more");
            return this;
        }
        setCurrent(s + CharNotInAlphabet.values()[count].getDes());
        count++;

        nowIndex = 0;
        ActivePoint current = new ActivePoint(root, ActivePoint.EMPTY, 0);
        for (char c : this.current.toCharArray()) {
            updateTree(current, c, nowIndex);
            nowIndex++;
        }
        initLeaf(root);
        //                dfs(root);
        //        bfs(root);
        return this;
    }

    private void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll == null) {
                System.out.println();
                continue;
            }
            System.out.print(poll.str + " ");
            for (Node node : poll.getChildren()) {
                queue.add(node);
            }
            queue.add(null);
        }
    }

    private void dfs(Node root) {
        System.out.println(root.str);
        int count = 0;
        for (Node node : root.getChildren()) {
            System.out.print(count++ + ":");
            dfs(node);
        }
    }

    public boolean contain(String s) {
        Node now = root;
        char[] aim = s.toCharArray();
        for (int i = 0; i < aim.length; ) {
            char c = aim[i];
            if (!now.containsChild(c)) {
                return false;
            }
            now = now.getChild(c);
            char[] chars = now.getStr().toCharArray();
            i++;
            for (int j = 1; i < aim.length && j < chars.length; j++, i++) {
                if (aim[i] != chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initLeaf(Node now) {
        if (now.isLeaf()) {
            now.initString(current.length());
        } else {
            now.getChildren().forEach(this::initLeaf);
        }
    }

    private class ActivePoint {
        static final char EMPTY = '\0';

        private Node now;
        private Character edgeChar;
        private int len;

        /**
         * The meaning of this was that the number of suffixes we had to actively insert
         * at the end of each step was 1 (always just the final character).
         */
        private int remainder = 1;

        ActivePoint(Node now, Character edgeChar, int len) {
            this.now = now;
            this.edgeChar = edgeChar;
            this.len = len;
        }

        boolean contains(char c) {
            return now.containsChild(c);
        }

        void containUpdate(char c) {
            remainder++;
            if (this.len == 0) {
                assert edgeChar == EMPTY;
                this.edgeChar = c;
                len = 1;
            } else {
                len++;
            }
        }

        void addNewNode(char input, int nowIndex, Node old) {
            Node toAdd = new Node(nowIndex);
            if (now.containsChild(edgeChar)) { //split/find node and add new node when already exist
                if (remainder > 0) {
                    Node insertPoint = now.findOrSplitNode(edgeChar, len);
                    insertPoint.addChild(input, toAdd);
                    if (old != null) { // rule two: set link
                        old.setLink(insertPoint);
                    }

                    remainder--;
                    updateAfterAdd(nowIndex);
                    addNewNode(input, nowIndex, insertPoint);
                }
            } else {
                now.addChild(input, toAdd);
                // reset active point
                edgeChar = EMPTY;
                len = 0;
            }
        }

        private void updateAfterAdd(int nowIndex) {
            if (now == root) {// rule one
                edgeChar = current.charAt(nowIndex - remainder + 1);
                len--;
                assert len >= 0;
            } else {// rule three
                now = now.getSuffixLink();
            }
        }
    }

    private void updateTree(ActivePoint current, char input, int nowIndex) {
        if (current.contains(input)) {//observation one
            current.containUpdate(input);
        } else {
            current.addNewNode(input, nowIndex, null);
        }
    }


    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        suffixTree.add("abcabxabcd");
        System.out.println(suffixTree.contain("abc"));
        System.out.println(suffixTree.contain("bxa"));
        System.out.println(suffixTree.contain("xac"));
        System.out.println(suffixTree.contain("bcab"));
    }
}
