package competition.leetcode.w68;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zzt on 1/21/18.
 * <p>
 * <h3></h3>
 */
public class ReorganizeStr {

    private static class Count {
        private int c;
        private char aChar;

        public Count(int c, char aChar) {
            this.c = c;
            this.aChar = aChar;
        }

        public int getC() {
            return c;
        }

        public char getaChar() {
            return aChar;
        }
    }

    public String reorganizeString(String S) {
        TreeMap<Count, Character> tree = new TreeMap<>(Comparator.comparingInt(Count::getC)
                .thenComparing(Count::getaChar));
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        int max = 0, other = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0) continue;
            char value = (char) (i + 'a');
            tree.put(new Count(map[i], value), value);
            if (max < map[i]) {
                other += max;
                max = map[i];
            } else {
                other += map[i];
            }
        }
        if (other < max - 1) return "";
        StringBuilder sb = new StringBuilder(S.length());
        Map.Entry<Count, Character> entry = tree.lastEntry();
        for (int i = 0; i < entry.getKey().getC(); i++) {
            sb.append(entry.getValue());
        }
        int s = 1, t = s, gap = s + 1;
        tree.remove(entry.getKey());
        while (!tree.isEmpty()) {
            Map.Entry<Count, Character> first = tree.lastEntry();
            tree.remove(first.getKey());
            for (Integer i = 0; i < first.getKey().getC(); i++) {
                sb.insert(t, first.getValue());
                t += gap;
                if (t > sb.length()) {
                    t = s;
                    gap = s + 1;
                    s++;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReorganizeStr f = new ReorganizeStr();
        System.out.println(f.reorganizeString("vvvlo"));
        System.out.println(f.reorganizeString("aaabc"));
        System.out.println(f.reorganizeString("aab"));
        System.out.println(f.reorganizeString("aaab"));
        System.out.println(f.reorganizeString("aaabbbccd"));
        System.out.println(f.reorganizeString("aaaabbbccd"));
        System.out.println(f.reorganizeString("aaaabbbbcccd"));
    }
}
