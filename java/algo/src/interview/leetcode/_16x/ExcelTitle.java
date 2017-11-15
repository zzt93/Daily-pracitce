package interview.leetcode._16x;

/**
 * Created by zzt on 11/14/17.
 * <p>
 * <h3></h3>
 */
public class ExcelTitle {

    public String convertToTitle(int n) {
        int r;
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            r = n % 26;
            n = n / 26;
            if (r == 0) {
                n--;
                res.insert(0, 'Z');
            } else {
                r--;
                res.insert(0, (char) (r + 'A'));
            }
        }
        return res.toString();
    }

    public int titleToNumber(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum = (c - 'A' + 1) + sum * 26;
        }
        return sum;
    }

    public static void main(String[] args) {
        ExcelTitle e = new ExcelTitle();
        System.out.println(e.convertToTitle(1));
        System.out.println(e.convertToTitle(234));
        System.out.println(e.convertToTitle(1234));
    }
}
