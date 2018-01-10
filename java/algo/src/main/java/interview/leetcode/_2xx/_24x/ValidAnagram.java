package interview.leetcode._2xx._24x;

/**
 * Created by zzt on 1/9/18.
 * <p>
 * <h3></h3>
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            map1[sc[i]-'a']++;
            map2[tc[i]-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i]) return false;
        }
        return true;
    }
}
