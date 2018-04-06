package interview.google.course;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static interview.google.course.Permutation.permutationNoDup;

/**
 * Created by zzt on 3/17/18.
 * <p>
 * <h3></h3>
 */
public class PalindromePermutation {

    public static List<String> palin(String s) {
        char[] cs = s.toCharArray();
        int[] map = new int[26];
        for (char c : cs) {
            map[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int odd = 0;
        char pivot = 0;
        for (int i = 0; i < 26; i++) {
            int c = map[i];
            for (int x = 0; x < c / 2; x++) sb.append((char) (i + 'a'));
            if (c % 2 == 1) {
                odd++;
                pivot = (char) (i + 'a');
            }
        }
        if (odd > 1) return Collections.emptyList();
        List<String> tmp = new LinkedList<>();
        List<String> res = new LinkedList<>();
        permutationNoDup(tmp, sb.toString().toCharArray(), 0);
        for (String p : tmp) {
            res.add(new StringBuilder(p).reverse().append(odd == 0 ? "" : pivot).append(p)
                    .toString());
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(palin("aabb"));
        System.out.println(palin("abc"));
    }
}
