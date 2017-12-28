package competition.leetcode.w56;

/**
 * Created by zzt on 10/29/17.
 * <p>
 * <h3></h3>
 */
public class StrCompression {

    public int compress(char[] chars) {
        char[] tmp = new char[chars.length];
        int res = 0;
        for (int i = 0; i < chars.length;) {
            char a = chars[i];
            int cnt = 0;
            while (i < chars.length && a == chars[i]) {
                i++;
                cnt++;
            }
            tmp[res++] = a;
            if (cnt > 1) {
                String s = "" + cnt;
                int l = s.length();
                System.arraycopy(s.toCharArray(), 0, tmp, res, l);
                res += l;
            }
        }
        System.arraycopy(tmp, 0, chars, 0, res);
        return res;
    }

    public static void main(String[] args) {
        StrCompression str = new StrCompression();
        System.out.println(str.compress(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(str.compress(new char[]{'a'}));
        System.out.println(str.compress(new char[]{'a','b'}));
        System.out.println(str.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }
}
