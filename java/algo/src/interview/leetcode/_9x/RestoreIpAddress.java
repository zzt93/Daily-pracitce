package interview.leetcode._9x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 8/1/17.
 * <p>
 * <h3></h3>
 */
public class RestoreIpAddress {

    public List<String> restoreIpAddresses(String str) {
        char[] chars = str.toCharArray();
        List<String> res = new ArrayList<>();
        recur(chars, 0, 0, res, new StringBuilder(chars.length + 3));
        return res;
    }

    private void recur(char[] chars, int s, int c, List<String> res, StringBuilder stringBuilder) {
        int l = chars.length;
        if (c == 4) {
            if (s == l) {
                stringBuilder.deleteCharAt(l + 3);
                res.add(stringBuilder.toString());
            }
            return;
        }
        if (s == chars.length) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (shorterOrLonger(c + 1, chars.length - s - i)) {
                continue;
            }
            if (!valid(chars, s, s + i)) {
                continue;
            }
            int t = stringBuilder.length();
            stringBuilder.append(chars, s, i).append('.');
            recur(chars, s + i, c + 1, res, stringBuilder);
            stringBuilder.delete(t, stringBuilder.length());
        }
    }

    private boolean shorterOrLonger(int c, int left) {
        return (4 - c) * 3 < left
                || left < (4 - c);
    }

    private boolean valid(char[] chars, int s, int e) {
        if (e - s == 3) {
            return chars[s] == '1' || (chars[s] == '2' && (chars[s + 1] == '5' && chars[s + 2] <=
                    '5' ||
                    chars[s + 1] < '5'));
        } else if (e - s == 2) {
            return chars[s] != '0';
        }
        return true;
    }

    public static void main(String[] args) {
        RestoreIpAddress r = new RestoreIpAddress();
        System.out.println(r.restoreIpAddresses("0000"));
        System.out.println(r.restoreIpAddresses("00000"));
        System.out.println(r.restoreIpAddresses("001000"));
        System.out.println(r.restoreIpAddresses("0010010"));
        System.out.println(r.restoreIpAddresses("0010001"));
        System.out.println(r.restoreIpAddresses("25525511135"));
    }
}
