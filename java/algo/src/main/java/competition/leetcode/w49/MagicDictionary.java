package competition.leetcode.w49;

import java.util.HashSet;

/**
 * Created by zzt on 9/10/17.
 * <p>
 * <h3></h3>
 */
public class MagicDictionary {

    private HashSet<String> set = new HashSet<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {

    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        set.clear();
        for (String s : dict) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                char old = sb.charAt(i);
                for (int x = 0; x < 26; x++) {
                    if (old - 'a' == x) {
                        continue;
                    }
                    sb.setCharAt(i, (char) ('a' + x));
                    set.add(sb.toString());
                }
                sb.setCharAt(i, old);
            }
        }
    }


    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return set.contains(word);
    }

    public static void main(String[] args) {
        new MagicDictionary().buildDict(new String[]{"hello", "leetcode"});
    }
}
