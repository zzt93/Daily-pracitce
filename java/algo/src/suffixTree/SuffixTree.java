package suffixTree;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 * A single string's suffix tree;
 * A set of string's generalized suffix tree(at most 3 string)
 */
public class SuffixTree {

    public static final String CHAR_NOT_IN_ALPHABET1 = "$";
    public static final String CHAR_NOT_IN_ALPHABET2 = "#";
    public static final String CHAR_NOT_IN_ALPHABET3 = "%";

    private ArrayList<String> lists = new ArrayList<>();
    private int nowIndex = 0;
    private Node root = new Node();

    private class Node {
        private int stringIndex;

        private int startIndex;
        private int endIndex;
        private Node suffixLink;

        private TreeMap<Character, Node> children = new TreeMap<>();

        private String getString() {
            return lists.get(stringIndex).substring(startIndex, endIndex);
        }
    }

    public void add(String s) {
        if (lists.size() >= 3) {
            return;
        }
        nowIndex = 0;
        lists.add(s);
        switch (lists.size()) {
            case 0:
                s += CHAR_NOT_IN_ALPHABET1;
                break;
            case 1:
                s += CHAR_NOT_IN_ALPHABET2;
                break;
            case 2:
                s += CHAR_NOT_IN_ALPHABET3;
                break;
        }
        for (char c : s.toCharArray()) {
            updateTree(c);
        }
    }

    private class ActivePoint {
        private Node node;
        private Character c;
        private int len;
    }

    private void updateTree(char c) {

    }


    public static void main(String[] args) {

    }
}
