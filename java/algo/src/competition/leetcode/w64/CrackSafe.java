package competition.leetcode.w64;

import java.util.HashSet;

/**
 * Created by zzt on 12/24/17.
 * <p>
 * <h3></h3>
 */
public class CrackSafe {

    public String crackSafe(int n, int k) {
        int kn = (int) Math.pow(k, n);
        StringBuilder sb = new StringBuilder(n + kn);
        for (int i = 0; i < n; i++) {
            sb.append('0');
        }
        HashSet<String> seen = new HashSet<>();
        seen.add(sb.toString());
        while (seen.size() < kn) {
            for (int i = k - 1; i >= 0; i--) {
                sb.append(((char) ('0' + i)));
                int l = sb.length();
                String sub = sb.substring(l - n);
                if (seen.contains(sub)) {
                    sb.deleteCharAt(l - 1);
                } else {
                    seen.add(sub);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CrackSafe c = new CrackSafe();
        System.out.println(c.crackSafe(1, 2));
        System.out.println(c.crackSafe(2, 2));
        System.out.println(c.crackSafe(3, 2));
        System.out.println(c.crackSafe(3, 10));
        System.out.println(c.crackSafe(4, 8));
    }
}
