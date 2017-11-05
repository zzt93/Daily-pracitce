package competition.leetcode.w57;

import java.util.*;

/**
 * Created by zzt on 11/5/17.
 * <p>
 * <h3></h3>
 */
public class LongestInDict {

    private static class Trie {
        private static class Node implements Comparable<Node> {
            boolean isEnd = false;
            char c;
            TreeMap<Character, Node> children = new TreeMap<>();

            public Node(char c) {
                this.c = c;
            }

            @Override
            public int compareTo(Node o) {
                return Character.compare(c, o.c);
            }

            public Node addChild(char c) {
                if (children.containsKey(c)) {
                    return children.get(c);
                }
                Node e = new Node(c);
                children.put(c, e);
                return e;
            }

            public void end() {
                isEnd = true;
            }
        }

        private Node empty = new Node((char) 0);

        public void add(String s) {
            Node now = empty;
            for (char c : s.toCharArray()) {
                now = now.addChild(c);
            }
            now.end();
        }

        public String dfs() {
            String res = "";
            for (Node child : empty.children.values()) {
                String tmp = dfs(child);
                if (tmp != null && tmp.length() > res.length()) {
                    res = tmp;
                }
            }
            return res;
        }

        private String dfs(Node now) {
            if (!now.isEnd) {
                return null;
            }
            String res = "";
            for (Node child : now.children.values()) {
                String dfs = dfs(child);
                if (dfs != null && res.length() < dfs.length()) {
                    res = dfs;
                }
            }
            return now.c + res;
        }
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        return trie.dfs();
    }

    public static void main(String[] args) {
        LongestInDict l = new LongestInDict();
        System.out.println(l.longestWord(new String[]{"a", "as", "asd"}));
        System.out.println(l.longestWord(new String[]{}));
        System.out.println(l.longestWord(new String[]{"as", "df"}));
        System.out.println(l.longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple", "b","ba","ban","bana","banan"}));
    }
}
