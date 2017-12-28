package interview.leetcode._2xx._20x;

/**
 * Created by zzt on 12/7/17.
 * <p>
 * <h3></h3>
 */
public class Trie {

    private static class TrieNode {
        char c;
        boolean end = false;
        TrieNode[] children = new TrieNode[26];

        TrieNode(char c) {
            this.c = c;
        }

        private TrieNode add(char c) {
            int i = c - 'a';
            if (children[i] == null) {
                children[i] = new TrieNode(c);
            }
            return children[i];
        }

        private TrieNode get(char c) {
            int i = c - 'a';
            return children[i];
        }

        private void setEnd() {
            end = true;
        }

        public boolean isEnd() {
            return end;
        }
    }

    /**
     * Initialize your data structure here.
     */
    private TrieNode root = new TrieNode('0');

    public Trie() {
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode now = root;
        for (char c : word.toCharArray()) {
            now = now.add(c);
        }
        now.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode now = root;
        for (char c : word.toCharArray()) {
            if (now == null) {
                return false;
            }
            now = now.get(c);
        }
        return now != null && now.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode now = root;
        for (char c : prefix.toCharArray()) {
            if (now == null) {
                return false;
            }
            now = now.get(c);
        }
        return now != null;
    }
}
