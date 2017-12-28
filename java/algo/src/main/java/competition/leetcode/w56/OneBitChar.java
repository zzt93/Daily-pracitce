package competition.leetcode.w56;

/**
 * Created by zzt on 10/29/17.
 * <p>
 * <h3></h3>
 */
public class OneBitChar {

    public boolean isOneBitCharacter(int[] bits) {
        boolean end = true;
        for (int i = 0; i < bits.length - 1; i++) {
            int bit = bits[i];
            if (bit == 0) {
                end = true;
            } else {
                end = !end;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        OneBitChar aChar = new OneBitChar();
        System.out.println(aChar.isOneBitCharacter(new int[]{0}));
        System.out.println(aChar.isOneBitCharacter(new int[]{1,0,0}));
        System.out.println(aChar.isOneBitCharacter(new int[]{1,1,1,0}));
        System.out.println(aChar.isOneBitCharacter(new int[]{1,1,1,0,0,1,0}));
    }
}
