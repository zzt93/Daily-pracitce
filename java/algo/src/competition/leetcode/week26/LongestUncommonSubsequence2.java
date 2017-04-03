package competition.leetcode.week26;

import java.util.*;

/**
 * Created by zzt on 4/2/17.
 * <p>
 * <h3></h3>
 */
public class LongestUncommonSubsequence2 {


    public int findLUSlength(String[] strs) {
        LinkedList<String> strings = new LinkedList<>();
        Collections.addAll(strings, strs);
        strings.sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        return findLUSlength(strings);
    }

    private int findLUSlength(LinkedList<String> ss) {
        if (ss.isEmpty()) {
            return -1;
        } else if (ss.size() == 1) {
            return ss.getFirst().length();
        }
        ListIterator<String> reverse = ss.listIterator(ss.size());
        String last = reverse.previous();
        String sec = reverse.previous();
        if (!last.equals(sec)) {
            return last.length();
        }
        int lastLen = last.length();
        ListIterator<String> rev = ss.listIterator(ss.size());
        while (rev.hasPrevious()) {
            String previous = rev.previous();
            if (previous.length() == lastLen && Objects.equals(previous, last)) {
                rev.remove();
            } else {
                HashSet<String> strings = new HashSet<>();
                strings.add("");
                allSub(last, 0, strings);
                ss.removeIf(strings::contains);
                return findLUSlength(ss);
            }
        }
        return -1;
    }

    private void allSub(String str, int len, HashSet<String> res) {
        if (len == str.length()) {
            return;
        }
        List<String> tmp = new ArrayList<>(res.size());
        for (String re : res) {
            tmp.add(re + str.charAt(len));
        }
        res.addAll(tmp);
        allSub(str, len + 1, res);
    }


    public static void main(String[] args) {
        LongestUncommonSubsequence2 subsequence2 = new LongestUncommonSubsequence2();
        System.out.println(subsequence2.findLUSlength(new String[]{"aabbcc", "aabbcc","c","e","aabbcd"}));
        System.out.println(subsequence2.findLUSlength(new String[]{"a", "b", "a", "b"}));
        System.out.println(subsequence2.findLUSlength(new String[]{"a", "aaa", "aaa"}));
        System.out.println(subsequence2.findLUSlength(new String[]{"e", "ab", "aa", "ba", "aba", "aba"}));
        System.out.println(subsequence2.findLUSlength(new String[]{"a", "ab", "aa", "ba", "aba", "aba"}));
        System.out.println(subsequence2.findLUSlength(new String[]{"e", "ab", "aa", "ba", "aaa", "aaa"}));
    }
}
