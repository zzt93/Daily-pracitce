package interview.leetcode._20x;

import java.util.HashMap;

/**
 * Created by zzt on 12/6/17.
 * <p>
 * <h3></h3>
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            if (map.containsKey(sc[i])) {
                if (map.get(sc[i]) != tc[i]) return false;
            } else if (map2.containsKey(tc[i])) {
                if (map2.get(tc[i]) != sc[i]) return false;
            } else {
                map.put(sc[i], tc[i]);
                map2.put(tc[i], sc[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings i = new IsomorphicStrings();
        System.out.println(i.isIsomorphic("aa", "ab"));
        System.out.println(i.isIsomorphic("ab", "aa"));
    }
}
