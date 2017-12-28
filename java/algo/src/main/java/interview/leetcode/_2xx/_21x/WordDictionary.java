package interview.leetcode._2xx._21x;

/**
 * Created by zzt on 12/8/17.
 * <p>
 * <h3></h3>
 */
public class WordDictionary {

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

    private TrieNode root = new TrieNode('0');

    public void addWord(String word) {
        TrieNode now = root;
        for (char c : word.toCharArray()) {
            now = now.add(c);
        }
        now.setEnd();
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.'
     * to represent any one letter.
     */
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }

    private boolean search(char[] cs, int i, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (i == cs.length) {
            return node.isEnd();
        }
        if (cs[i] == '.') {
            for (TrieNode child : node.children) {
                if (search(cs, i + 1, child)) return true;
            }
            return false;
        } else {
            return search(cs, i + 1, node.get(cs[i]));
        }
    }

    public WordDictionary() {

    }

}
