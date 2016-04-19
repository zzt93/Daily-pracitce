package suffixTree;

import java.util.*;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 * <a href="http://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english">
 * suffix tree implementation chat</a>
 * <li>A single string's suffix tree;</li>
 * <li>A set of string's generalized suffix tree(at most 3 string)</li>
 * <ul>
 * <li>The alternative I used to building a generalised suffix tree is to concatenate
 * the strings, and build a regular suffix tree</li>
 * </ul>
 */
public class SuffixTree {


    private static final int NOT_CONTAIN_END_SYMBOL = -1;
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

        public static boolean inIt(char c) {
            String str = "" + c;
            for (CharNotInAlphabet charNotInAlphabet : values()) {
                if (str.equals(charNotInAlphabet.getDes())) {
                    return true;
                }
            }
            return false;
        }
    }

    private int count = 0;
    private StringBuilder current = new StringBuilder();
    private Node root = new Node(0, "");

    public SuffixTree() {
        root.setLink(root);
    }

    private class Node {
        // used in the construction phase
        private int startIndex;
        // used in look up phase
        private String str;
        private Node suffixLink = root;

        private TreeMap<Character, Node> children = new TreeMap<>();
        private TreeSet<Integer> labels = new TreeSet<>();

        Node(int startIndex) {
            this.startIndex = startIndex;
            labels.add(count);
        }

        /**
         * This constructor is for {@link #splitStringAndAddNode(int)} and root
         * which should not {@link #markLabel()}
         *
         * @param i startIndex
         * @param s string for look up
         */
        Node(int i, String s) {
            startIndex = i;
            str = s;
        }

        void markLabel() {
            labels.add(count);
        }

        Collection<Node> getChildren() {
            return children.values();
        }

        Node findOrSplitNode(char edge, int activeLen) {
            Node node = getChild(edge);
            Node father = this;
            assert node != null;
            assert node.edgeLength() > activeLen;
            if (activeLen != 0) {// find the node, and need to split
                // split node by setting endIndex and set string
                // add the rest of splitting node
                node = node.splitStringAndAddNode(activeLen);
                father.updateChild(edge, node);
            }
            return node;
        }

        private void updateChild(char c, Node node) {
            assert children.containsKey(c);
            children.put(c, node);
        }

        private Node getChild(char c) {
            Node node = children.get(c);
            if (node == null) {
                return null;
            }
            node.markLabel();
            return node;
        }

        int getStartIndex() {
            return startIndex;
        }

        private int edgeLength() {
            if (str == null) {
                return nowIndex - startIndex + 1;
            }
            return str.length();
        }

        void addChild(char newSon, Node node) {
            children.put(newSon, node);
        }

        private Node splitStringAndAddNode(int activeLen) {
            int endIndex = activeLen + getStartIndex();
            Node newThis = new Node(getStartIndex(), current.substring(getStartIndex(), endIndex));
            // newThis should have the same labels with original one
            newThis.markLabel(this.getLabels());
            // remove current label from new son: it's not appear in the new string for the time being
            // TODO: 4/19/16 wrong
            //            this.removeLabel();

            // change current node to it's son
            moveBack(endIndex);
            char newSon = current.charAt(endIndex);
            newThis.addChild(newSon, this);
            return newThis;
        }

        private void removeLabel() {
            labels.remove(count);
        }

        private void markLabel(TreeSet<Integer> labels) {
            this.labels.addAll(labels);
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            labels.forEach(i -> sb.append(i).append(" "));
            return hashCode() + ":(" + str + ", " + sb +
                    ")->(" + suffixLink.hashCode() + ") ";
        }

        TreeSet<Integer> getLabels() {
            return labels;
        }
    }

    public SuffixTree add(String s) {
        if (count >= CharNotInAlphabet.values().length) {
            System.err.println("can't add more");
            return this;
        }
        //        String next = s;
        String next = s + CharNotInAlphabet.values()[count].getDes();
        addCurrent(next);
        count++;

        ActivePoint current = new ActivePoint(root, ActivePoint.EMPTY, 0);
        root.markLabel();
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
            System.out.print(poll.toString());
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

    /**
     * determine whether s is a substring of T(added before)
     *
     * @param s input string
     *
     * @return true is contain
     */
    public boolean contain(String s) {
        char next = 'a';
        Traverse traverse = new Traverse(s, next).invoke();
        return traverse.find();
    }

    /**
     * check whether s is a suffix of T(added before)
     *
     * @param s input string
     *
     * @return true if it is suffix
     */
    public boolean isSuffix(String s) {
        char next = CharNotInAlphabet.DOLOR.getDes().charAt(0);
        Traverse traverse = new Traverse(s, next).invoke();
        if (!traverse.find()) {
            return false;
        }
        next = traverse.getNext();
        return CharNotInAlphabet.inIt(next);
    }

    /**
     * @param s input string
     *
     * @return repeat time
     */
    public int repeatTime(String s) {
        return 0;
    }

    /**
     * find a substring which appear more than once
     *
     * <li>ooooo -> oooo</li>
     * <li>cdddcdc -> cd/dd/dc</li>
     * @implNote Find the node that has longest path from root and at least 2 leaves under it.
     *
     * @return longest repeat string
     */
    public String longestRepeat() {
        ArrayList<String> tmp = repeat();
        return tmp.stream()
                .max((s1, s2) -> new Integer(s1.length()).compareTo(s2.length()))
                .orElseGet(String::new);
    }

    public ArrayList<String> repeat() {
        ArrayList<String> res = new ArrayList<>();
        findRepeatString(root, res, "");
        return res;
    }

    private void findRepeatString(Node now, ArrayList<String> res, String s) {
        Collection<Node> children = now.getChildren();
        String next;
        if (children.size() >= 2) {
            next = s.concat(now.getStr());
        } else {
            return;
        }
        for (Node child : children) {
            findRepeatString(child, res, next);
        }
        res.add(next);
    }

    public ArrayList<String> commonString() {
        List<String> target = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            target.add(CharNotInAlphabet.values()[i].getDes());
        }
        ArrayList<String> res = new ArrayList<>();
        containTarget(root, target, res);
        return res;
    }

    private int containTarget(Node now, List<String> target, ArrayList<String> res) {
        // TODO: 4/19/16 implement it
        TreeSet<Integer> set = new TreeSet<>();
        for (Node node : now.getChildren()) {
            int i = containTarget(node, target, res);
            if (i >= 0 && i < count) {
                set.add(i);
                if (set.size() == target.size()) {

                }
            }
        }
        String str = now.getStr();
        for (int i = 0; i < target.size(); i++) {
            String s = target.get(i);
            if (str.contains(s)) {
                return i;
            }
        }
        return NOT_CONTAIN_END_SYMBOL;
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
        /**
         * @inv if (edgeChar == EMPTY) {assert len == 0}
         */
        private Character edgeChar;
        /**
         * @inv if (len == 0) {assert edgeChar == EMPTY}
         */
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
         * When the final suffix we need to insert is found to
         * exist in the same edge or decedent edge
         * already
         *
         * @param c final suffix
         *
         * @return result
         */
        boolean contains(char c) {
            if (len == 0) {
                assert edgeChar == EMPTY;
                return now.containsChild(c);
            } else {// this branch assure activeLen < edgeLength
                assert now.containsChild(edgeChar);
                Node child = now.getChild(edgeChar);
                assert child != null;
                Node father = now;
                char nextChar = edgeChar;
                int activeLen = len;
                while (child.edgeLength() <= activeLen) {// observation two
                    /**
                     * make {@link #now} {@link #len}, {@link #edgeChar} consistent
                     */
                    activeLen -= child.edgeLength();
                    assert activeLen >= 0;
                    nextChar = current.charAt(nowIndex - activeLen);
                    father = child;

                    child = child.getChild(nextChar);
                    if (child == null) {
                        assert activeLen == 0;
                        break;
                    }
                }
                /**
                 * move active point so no need to move again in
                 * {@link #containUpdate(char)}
                 * {@link #addNewNode(char, int, Node)}
                 */
                if (activeLen == 0) { // just check char from node by map, no need to look more
                    moveActivePoint(father, EMPTY, activeLen);
                    return father.containsChild(nextChar);
                }
                moveActivePoint(father, nextChar, activeLen);
                return getChar(child.getStartIndex() - 1 + len + 1) == c;
            }
        }

        private void moveActivePoint(Node child, char fatherChar, int activeLen) {
            now = child;
            edgeChar = fatherChar;
            len = activeLen;
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
            assert child != null;
            assert len <= child.edgeLength() : "assured by contains";
        }

        private void moveActivePoint(Node node) {
            now = node;
        }

        void addNewNode(char input, int nowIndex, Node old) {
            Node toAdd = new Node(nowIndex);
            if (now.containsChild(edgeChar)) { //split/find node and add new node when already exist
                if (remainder > 0) {
                    Node insertPoint = now.findOrSplitNode(edgeChar, len);
                    insertPoint.addChild(input, toAdd);
                    if (old != null) { // rule two: set link
                        // the insertPoint's string is the suffix of old's, so
                        // if we insert a new suffix into old, then we have to
                        // insert it into insertPoint
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
                moveActivePoint(now.getSuffixLink());
            }
        }
    }

    private char getChar(int i) {
        return current.charAt(i);
    }

    private void updateTree(ActivePoint current, char input, int nowIndex, Node insertPoint) {
        if (current.contains(input)) {//observation one
            // TODO: 4/18/16 also set link?
            current.containUpdate(input);
        } else {
            current.addNewNode(input, nowIndex, insertPoint);
        }
    }


    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
//        suffixTree.add("cdddcdc");
        //        StringBuilder sb = new StringBuilder("bacab");
        //        suffixTree.add(sb.toString());
        //        suffixTree.add(sb.reverse().toString());
        //                suffixTree.add("abcabxabcd");
        //                suffixTree.add("abcdefabxybcdmnabcdex");
        //                suffixTree.add("abcadak");
        //                suffixTree.add("dedododeeodo");
                        suffixTree.add("ooooooooo");
        //                suffixTree.add("mississippi");
//        suffixTree.add("abacad");
        System.out.println(suffixTree.contain("ab"));
        System.out.println(suffixTree.contain("ba"));
        System.out.println(suffixTree.contain("cd"));
        System.out.println(suffixTree.contain("cddd"));
        System.out.println(suffixTree.contain("cddde"));
        System.out.println(suffixTree.contain("bab"));
        System.out.println(suffixTree.contain("bac"));
        System.out.println(suffixTree.contain("baca"));
        System.out.println(suffixTree.isSuffix("baca"));
        System.out.println(suffixTree.longestRepeat());
    }

    private class Traverse {
        private boolean find;
        private String s;
        private char next;

        Traverse(String s, char next) {
            this.s = s;
            this.next = next;
        }

        boolean find() {
            return find;
        }

        char getNext() {
            return next;
        }

        Traverse invoke() {
            Node now = root;
            char[] aim = s.toCharArray();
            for (int i = 0; i < aim.length; ) {
                char c = aim[i];
                if (!now.containsChild(c)) {
                    find = false;
                    return this;
                }
                now = now.getChild(c);
                assert now != null;
                char[] chars = now.getStr().toCharArray();
                i++;
                int j;
                for (j = 1; i < aim.length && j < chars.length; j++, i++) {
                    if (aim[i] != chars[j]) {
                        find = false;
                        return this;
                    }
                }
                if (j < chars.length) {
                    next = chars[j];
                }
            }
            find = true;
            return this;
        }
    }
}
