package interview.google.course;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 3/17/18.
 * <p>
 * <h3></h3>
 */
public class Permutation {

    public static void permutation(List<String> res, char[] cs, int selected) {
        if (selected == cs.length) res.add(new String(cs));
        else {
            for (int x = selected; x < cs.length; x++) {
                swap(cs, selected, x);
                permutation(res, cs, selected + 1);
                swap(cs, selected, x);
            }
        }
    }

    public static void permutationNoDup(List<String> res, char[] cs, int i) {
        if (i == cs.length) res.add(new String(cs));
        else {
            int[] map = new int[26];
            for (int x = i; x < cs.length; x++) {
                if (map[cs[x]-'a'] == 1) continue;
                map[cs[x]-'a'] = 1;
                swap(cs, i, x);
                permutationNoDup(res, cs, i+1);
                swap(cs, i, x);
            }
        }
    }

    public static void combination(List<String> res, StringBuilder sb, char[] cs, int i, int n) {
        if (sb.length() == n) res.add(sb.toString());
        else {
            if (i >= cs.length) return;
            sb.append(cs[i]);
            combination(res, sb, cs, i+1, n);
            sb.deleteCharAt(sb.length()-1);
            combination(res, sb, cs, i+1, n);
        }
    }

    private static void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }

    public static void permu(String s) {
        LinkedList<String> res2 = new LinkedList<>();
        permutationNoDup(res2, s.toCharArray(), 0);
        System.out.println(res2);
        LinkedList<String> res = new LinkedList<>();
        permutation(res, s.toCharArray(), 0);
        System.out.println(res);
    }

    public static void comb(String s, int n) {
        LinkedList<String> res = new LinkedList<>();
        combination(res, new StringBuilder(), s.toCharArray(), 0, n);
        System.out.println(res);
    }

    public static void main(String[] args) {
        permu("aabb");
        permu("abc");
//        comb("abc", 2);
//        comb("abc", 1);
    }
}
