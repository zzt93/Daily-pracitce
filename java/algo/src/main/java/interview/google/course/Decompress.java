package interview.google.course;

/**
 * Created by zzt on 3/16/18.
 * <p>
 * <h3></h3>
 */
public class Decompress {

    public String decompress(String s) {
        char[] cs = s.toCharArray();
        return recur(cs, 0, cs.length).toString();
    }

    /**
     * pre: [s, e), xx[yy]zz
     */
    private StringBuilder recur(char[] cs, int s, int e) {
        if (noBracket(cs, s, e)) return new StringBuilder(new String(cs, s, e-s));
        StringBuilder num = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = s; i < e; i++) {
            if (Character.isDigit(cs[i])) num.append(cs[i]);
            else if (cs[i] == '[') {
                int n = Integer.parseInt(num.toString());
                num.setLength(0);
                int endBracket = findMatch(cs, i + 1, e);
                StringBuilder sub = recur(cs, i + 1, endBracket);
                i = endBracket;
                for (int j = 0; j < n; j++) res.append(sub);
            } else {
                res.append(cs[i]);
            }
        }
        return res;
    }

    private boolean noBracket(char[] cs, int s, int e) {
        for (int i = s; i < e; i++) {
            if (cs[i] == '[') return false;
        }
        return true;
    }

    private int findMatch(char[] cs, int s, int e) {
        int leftC = 0;
        for (int i = s; i < e; i++) {
            if (leftC == 0 && cs[i] == ']') return i;
            else if (cs[i] == '[') leftC++;
            else if (cs[i] == ']') leftC--;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        Decompress d = new Decompress();
        System.out.println(d.decompress("10[a]"));
        System.out.println(d.decompress("3[abc]4[ab]c"));
        System.out.println(d.decompress("2[a2[b]c]"));
    }
}
