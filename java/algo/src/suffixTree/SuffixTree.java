package suffixTree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 * <li>A single string's suffix tree;</li>
 * <li>A set of string's generalized suffix tree(at most 3 string)</li>
 * <ul>
 * <li>The alternative I used to building a generalised suffix tree is to concatenate
 * the strings, and build a regular suffix tree</li>
 * </ul>
 */
public class SuffixTree {


    private int nowIndex = 0;

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
    private StringBuilder current = new StringBuilder();
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

        Node(String substring) {
            str = substring;
        }

        Collection<Node> getChildren() {
            return children.values();
        }

        Node findOrSplitNode(char edge, int activeLen) {
            Node node = children.get(edge);
            Node father = this;
            char fatherChar = edge;
            while (node.edgeLength() <= activeLen) {// observation two
                activeLen -= node.edgeLength();
                if (activeLen == 0) {
                    break;
                }
                father = node;
                fatherChar = current.charAt(nowIndex - activeLen);
                node = node.getChild(fatherChar);
                assert node != null;
            }
            if (activeLen != 0) {// find the node, and need to split
                // split node by setting endIndex and set string
                // add the rest of splitting node
                node = node.splitStringAndAddNode(activeLen);
                father.addChild(fatherChar, node);
            }
            return node;
        }

        private Node getChild(char c) {
            return children.get(c);
        }

        public int getStartIndex() {
            return startIndex;
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

        private Node splitStringAndAddNode(int activeLen) {
            int endIndex = activeLen + getStartIndex();
            Node newThis = new Node(current.substring(getStartIndex(), endIndex));
            // change current node to it's son
            moveBack(endIndex);
            char newSon = current.charAt(endIndex);
            newThis.addChild(newSon, this);
            return newThis;
        }

        private void moveBack(int newStartIndex) {
            if (str != null) {
                str = str.substring(newStartIndex - startIndex);
            }
            startIndex = newStartIndex;
        }

        private void initString(int endIndex) {
            str = current.substring(startIndex, endIndex);
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
            return str == null && children.isEmpty();
        }

        String getStr() {
            return str;
        }
    }

    public SuffixTree add(String s) {
        if (count >= CharNotInAlphabet.values().length) {
            System.err.println("can't add more");
            return this;
        }
        String next = s + CharNotInAlphabet.values()[count].getDes();
        addCurrent(next);
        count++;

        ActivePoint current = new ActivePoint(root, ActivePoint.EMPTY, 0);
        for (char c : next.toCharArray()) {
            updateTree(current, c, nowIndex, null);
            nowIndex++;
        }
        initLeaf(root);
        //                dfs(root);
        bfs(root);
        return this;
    }

    private void addCurrent(String s) {
        current.append(s);
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
            queue.addAll(poll.getChildren());
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

        /**
         * When the final suffix we need to insert is found to exist in the same edge already
         *
         * @param c final suffix
         *
         * @return result
         */
        boolean contains(char c) {
            if (edgeChar == EMPTY) {
                return now.containsChild(c);
            } else {
                assert now.containsChild(edgeChar);
                Node child = now.getChild(edgeChar);
                return getChar(child.getStartIndex() - 1 + len + 1) == c;
            }
        }

        void containUpdate(char c) {
            remainder++;
            if (this.len == 0) {// no need to find out, just insert at this node
                assert edgeChar == EMPTY;
                this.edgeChar = c;
                len = 1;
            } else {
                // recognize this char so proceed one
                len++;
            }
            Node child = now.getChild(edgeChar);
            if (len == child.edgeLength()) {//if this node is not enough for next loop up
                now = child;
                edgeChar = EMPTY;
                len -= child.edgeLength();
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
                    updateTree(this, input, nowIndex, insertPoint);
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
                if (len == 0) {
                    edgeChar = EMPTY;
                }
            } else {// rule three
                now = now.getSuffixLink();
            }
        }
    }

    private char getChar(int i) {
        return current.charAt(i);
    }

    private void updateTree(ActivePoint current, char input, int nowIndex, Node insertPoint) {
        if (current.contains(input)) {//observation one
            current.containUpdate(input);
        } else {
            current.addNewNode(input, nowIndex, insertPoint);
        }
    }


    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        StringBuilder sb = new StringBuilder("bacab");
        suffixTree.add(sb.toString());
        suffixTree.add(sb.reverse().toString());
        //        suffixTree.add("bacab");
        //        suffixTree.add("abcabxabcd");
        System.out.println(suffixTree.contain("ab"));
        System.out.println(suffixTree.contain("ba"));
        System.out.println(suffixTree.contain("ac"));
        System.out.println(suffixTree.contain("bab"));
    }
}
