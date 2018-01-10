package competition.leetcode.w66;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by zzt on 1/7/18.
 * <p>
 * <h3></h3>
 */
public class SpecialBinaryString {

    public String makeLargestSpecial(String s) {
        char[] cs = s.toCharArray();
        boolean zero = false;
        int zc = 0, oc = 0;
        for (char c : cs) {
            if (c == '0') {
                zero = true;
                zc++;
            } else {
                if (zero) break;
                oc++;
            }
        }
        int gap = oc - zc;
        zc = oc = 0;
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = gap; i < cs.length - gap; i++) {
            char c = cs[i];
            if (c == '0') zc++;
            else oc++;
            if (zc == oc) {
                pairs.add(new String(cs, i - 2 * zc + 1, 2 * zc));
                zc = oc = 0;
            }
        }
        Comparator<String> tComparator = Comparator.naturalOrder();
        pairs.sort(tComparator.reversed());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gap; i++) {
            sb.append("1");
        }
        for (String pair : pairs) {
            sb.append(pair);
        }
        for (int i = 0; i < gap; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SpecialBinaryString s = new SpecialBinaryString();
        System.out.println(s.makeLargestSpecial("10110100"));
        System.out.println(s.makeLargestSpecial("11011000"));
        System.out.println(s.makeLargestSpecial("1101101001110000"));
    }
}
