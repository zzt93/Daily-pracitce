package interview.leetcode._3xx._30x;

/**
 * Created by zzt on 2/2/18.
 * <p>
 * <h3></h3>
 */
public class AdditiveNum {

    public boolean isAdditiveNumber(String num) {
        char[] cs = num.toCharArray();
        int max = (cs.length + 1) / 2;
        for (int i = 1; i < max; i++) {
            if (cs[0] == '0' && i > 1) break;
            long f = Long.parseLong(new String(cs, 0, i));
            for (int x = i + 1; x - i < max; x++) {
                if (cs[i] == '0' && x - i > 1) break;
                long s = Long.parseLong(new String(cs, i, x - i));
                long t1 = f, t2 = s;
                for (int k = x; k < cs.length; ) {
                    long aim = t1 + t2;
                    char[] t = (aim + "").toCharArray();
                    int y;
                    for (y = 0; y < t.length && k < cs.length; k++, y++) {
                        if (cs[k] != t[y]) break;
                    }
                    if (y == t.length) {
                        t1 = t2;
                        t2 = aim;
                        if (k == cs.length) return true;
                    } else break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AdditiveNum a = new AdditiveNum();
        System.out.println(a.isAdditiveNumber("111122335588143"));
        System.out.println(a.isAdditiveNumber("199111992"));
        System.out.println(a.isAdditiveNumber("12021"));
        System.out.println(a.isAdditiveNumber("112358"));
        System.out.println(a.isAdditiveNumber("199100199"));
        System.out.println(a.isAdditiveNumber("0112"));
        System.out.println(a.isAdditiveNumber("1011"));
        System.out.println(a.isAdditiveNumber("101123581321345589144"));
        System.out.println(a.isAdditiveNumber(""));
        System.out.println(a.isAdditiveNumber("12"));
        System.out.println(a.isAdditiveNumber("1203"));
        System.out.println(a.isAdditiveNumber("1012"));
    }
}
