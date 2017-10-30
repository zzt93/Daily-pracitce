package interview.leetcode._14x;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zzt on 10/28/17.
 * <p>
 * <h3>Similar</h3>
 * <p>{@link interview.leetcode._13x.PalindromePartition}</p>
 * <h3>Memorized DFS!</h3>
 */
public class WordBreak2 {
    public List<String> wordBreakTLE(String str, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        List<List<String>> lists = new ArrayList<>();
        int l = str.length();
        char[] chars = str.toCharArray();

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        String[][] dp = new String[l][l + 1];
        for (int s = 0; s < l; s++) {
            if (!set.contains(s)) {
                continue;
            }
            for (int e = s + 1; e < (l + 1); e++) {
                String o = new String(chars, s, e - s);
                if (dict.contains(o)) {
                    dp[s][e] = o;
                    set.add(e);
                }
            }

        }
        simulate(dp, chars, 0, lists, new LinkedList<>());
        return lists.stream().map(list -> list.stream().collect(Collectors.joining(" "))).collect
                (Collectors.toList());
    }

    private void simulate(String[][] dp, char[] cs, int s, List<List<String>> res,
                          LinkedList<String> tmp) {
        if (s == cs.length) {
            res.add(new ArrayList<>(tmp));
        } else {
            for (int e = s + 1; e < dp[s].length; e++) {
                if (dp[s][e] != null) {
                    tmp.add(dp[s][e]);
                    simulate(dp, cs, e, res, tmp);
                    tmp.removeLast();
                }
            }
        }
    }

    public List<String> wordBreak(String str, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        return dfs(str.toCharArray(), 0, dict, new HashMap<>());
    }

    private List<String> dfs(char[] cs, int s, HashSet<String> dict, HashMap<Integer, List<String>>
            map) {

        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> res = new ArrayList<>();
        for (int e = s + 1; e < cs.length + 1; e++) {
            String o = new String(cs, s, e - s);
            if (dict.contains(o)) {
                if (e == cs.length) {
                    res.add(o);
                    break;
                }
                List<String> dfs = dfs(cs, e, dict, map);
                for (String df : dfs) {
                    res.add(o + " " + df);
                }

            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreak2 break2 = new WordBreak2();
        System.out.println(break2.wordBreak
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Lists.newArrayList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        System.out.println(break2.wordBreak("a", Lists.newArrayList()));
        System.out.println(break2.wordBreak("a", Lists.newArrayList("b")));
        System.out.println(break2.wordBreak("catsend", Lists.newArrayList("cats",
                "send", "end",
                "cat")));
        System.out.println(break2.wordBreak("catsendog", Lists.newArrayList("cats",
                "send",
                "end", "cat", "dog", "og", "sen")));
    }
}
