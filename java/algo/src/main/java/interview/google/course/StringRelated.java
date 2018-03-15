package interview.google.course;

import interview.leetcode._2xx._21x.ShortestPalindrome;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzt on 3/15/18.
 * <p>
 * <h3></h3>
 */
public class StringRelated {

    public String stringSplosion(String str) {
        int l = str.length();
        StringBuilder sb = new StringBuilder(l * l);
        for (int i = 1; i <= l; i++) {
            sb.append(str.substring(0, i));
        }
        return sb.toString();
    }

    public static String withoutString(String base, String remove) {
        char[] cs = base.toCharArray();
        char[] r = remove.toCharArray();
        StringBuilder sb = new StringBuilder(cs.length);
        int i;
        for (i = 0; i <= cs.length - r.length; ) {
            int j, tmp = i;
            for (j = 0; j < r.length; j++) {
                if (Character.toLowerCase(cs[tmp++]) != Character.toLowerCase(r[j])) {
                    break;
                }
            }
            if (j == r.length) {
                i += r.length;
            } else {
                sb.append(cs[i]);
                i++;
            }
        }
        return sb.append(base.substring(i)).toString();
    }

    public static int sumNumbers(String str) {
        Pattern num = Pattern.compile("\\d+");
        Matcher m = num.matcher(str);
        int sum = 0;
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        return sum;
    }


    public static int sumNumbers2(String str) {
        int state = 0;
        char[] cs = (str + " ").toCharArray();
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (state == 0) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                    state = 1;
                }
            } else {
                if (!Character.isDigit(c)) {
                    sum += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                    state = 0;
                } else {
                    sb.append(c);
                }
            }
        }
        return sum;
    }

    public static int[] kmp(String s) {
        char[] cs = s.toCharArray();
        int[] res = new int[cs.length];
        res[0] = 0;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[res[i - 1]]) {
                res[i] = res[i - 1] + 1;
            } else {
                int lastPrefix = res[i - 1] - 1;
                while (lastPrefix >= 0 && cs[i] != cs[res[lastPrefix]]) {
                    lastPrefix = res[lastPrefix] - 1;
                }
                if (lastPrefix >= 0) {
                    res[i] = res[lastPrefix] + 1;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(kmp("hello hello")));
        System.out.println(Arrays.toString(ShortestPalindrome.kmp("hello hello")));
        System.out.println(Arrays.toString(kmp("ABC ABCDAB ABCDABCDABDE")));
        System.out.println(Arrays.toString(ShortestPalindrome.kmp("ABC ABCDAB ABCDABCDABDE")));
//        System.out.println(sumNumbers("xy12asd23df3"));
//        System.out.println(withoutString("hello there", "llo"));
//        System.out.println(withoutString("hello hello", "llo"));
//        System.out.println(withoutString("hello hello1", "llo"));
    }

}
