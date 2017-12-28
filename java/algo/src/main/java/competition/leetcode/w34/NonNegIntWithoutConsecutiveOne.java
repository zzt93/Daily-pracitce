package competition.leetcode.w34;

/**
 * Created by zzt on 5/28/17.
 * <p>
 * <h3></h3>
 */
@Deprecated
public class NonNegIntWithoutConsecutiveOne {

    public static final int MASK = 3;
    public static final int MASK2 = 2;


    public int findIntegers(int num) {
        return 0;
    }


    public static void main(String[] args) {
        NonNegIntWithoutConsecutiveOne one = new NonNegIntWithoutConsecutiveOne();
        //        System.out.println((one.findIntegers(0x1)));
        //        System.out.println((one.findIntegers(0x5)));
        //        System.out.println((one.findIntegers(0x6)));
        //        System.out.println((one.findIntegers(0x7)));
        System.out.println((one.findIntegers(0x8)));
        System.out.println((one.findIntegers(0x9)));
        System.out.println((one.findIntegers(0xf)));
        System.out.println((one.findIntegers(0xabc)));
        System.out.println((one.findIntegers(0xffffff)));
        System.out.println((one.findIntegers(0x1ffffff)));
    }

}
