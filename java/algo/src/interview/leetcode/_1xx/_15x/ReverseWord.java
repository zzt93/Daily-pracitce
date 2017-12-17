package interview.leetcode._1xx._15x;

/**
 * Created by zzt on 11/8/17.
 * <p>
 * <h3></h3>
 */
public class ReverseWord {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String trim = s.trim();
        for (int i = 0; i < trim.length(); i++) {
            sb.append(trim.charAt(i));
            if (trim.charAt(i) == ' ') {
                int t = i + 1;
                while (t < trim.length() && trim.charAt(t) == ' ') {
                    t++;
                }
                i = t - 1;
            }
        }
        sb.reverse();
        int l = sb.length();
        for (int i = 0; i < l; i++) {
            int st = i;
            while (i < l && sb.charAt(i) != ' ') {
                i++;
            }
            reverse(sb, st, i);
        }
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int s, int e) {
        for (int i = s; i < (e + s) / 2; i++) {
            int o = e + s - 1 - i;
            char tmp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(o));
            sb.setCharAt(o, tmp);
        }
    }

    public static void main(String[] args) {
        ReverseWord r = new ReverseWord();
        System.out.println(r.reverseWords(""));
        System.out.println(r.reverseWords("  "));
        System.out.println(r.reverseWords("asdf"));
        System.out.println(r.reverseWords("the sky is blue"));
        System.out.println(r.reverseWords("  the    sky is blue"));
    }
}
