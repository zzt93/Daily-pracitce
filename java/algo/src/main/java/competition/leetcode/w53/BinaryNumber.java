package competition.leetcode.w53;

/**
 * Created by zzt on 10/8/17.
 * <p>
 * <h3></h3>
 */
public class BinaryNumber {

    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

    public static void main(String[] args) {
        BinaryNumber number = new BinaryNumber();
        System.out.println(number.hasAlternatingBits(5));
        System.out.println(number.hasAlternatingBits(0));
        System.out.println(number.hasAlternatingBits(1));
        System.out.println(number.hasAlternatingBits(2));
        System.out.println(number.hasAlternatingBits(3));
        System.out.println(number.hasAlternatingBits(7));
        System.out.println(number.hasAlternatingBits(8));
    }
}
