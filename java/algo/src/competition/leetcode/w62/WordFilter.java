package competition.leetcode.w62;

import interview.leetcode._2xx._20x.Trie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zzt on 12/10/17.
 * <p>
 * <h3></h3>
 */
public class WordFilter {

    private static class TrieNode {
        private final char c;
        private TrieNode[] children = new TrieNode[26];
        private List<Integer> wei = new ArrayList<>();

        public TrieNode(char c) {
            this.c = c;
        }

        TrieNode add(char c, int w) {
            int i = c - 'a';
            if (children[i] == null) {
                children[i] = new TrieNode(c);
            }
            children[i].wei.add(w);
            return children[i];
        }

        TrieNode get(char c) {
            return children[c - 'a'];
        }

        void addW(int i) {
            wei.add(i);
        }
    }

    private TrieNode root = new TrieNode('0');
    private TrieNode reverse = new TrieNode('0');

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            TrieNode now = this.root;
            TrieNode reverse = this.reverse;
            this.root.addW(i);
            this.reverse.addW(i);
            char[] chars = words[i].toCharArray();
            for (char aChar : chars) {
                now = now.add(aChar, i);
            }
            for (int x = chars.length - 1; x >= 0; x--) {
                reverse = reverse.add(chars[x], i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode now = this.root;
        TrieNode reverse = this.reverse;
        for (char c : prefix.toCharArray()) {
            now = now.get(c);
            if (now == null) {
                return -1;
            }
        }
        for (int i = suffix.toCharArray().length - 1; i >= 0; i--) {
            char c = suffix.toCharArray()[i];
            reverse = reverse.get(c);
            if (reverse == null) {
                return -1;
            }
        }
        now.wei.retainAll(reverse.wei);
        return now.wei.stream().max(Comparator.naturalOrder()).get();
    }

    private static void test(String[] ss, String p, String s) {
        WordFilter wordFilter = new WordFilter(ss);
        System.out.println(wordFilter.f(p, s));
    }

    public static void main(String[] args) {
        test(new String[]{"apple", "aaee"}, "a", "");
        test(new String[]{"apple"}, "a", "e");
        test(new String[]{"apple"}, "b", "");
    }

}
