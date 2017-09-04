package pearls;

import java.util.Arrays;

/**
 * Created by zzt on 4/28/16.
 * <h3>Problem</h3>
 * <p>This models the problem of swapping nonadjacent blocks of memory: abc -> cba</p>
 * <h3>Solution</h3>
 * abc -> abc1c2; len(c2) == len(a) -> c2bc1a; ->
 * solve sub-problem `bc1 -> c1b` -> c2c1ba -> c1c2ba
 * <hr>
 * abc -> ab1b2c; rotate -> b2cab1 -> solve sub-problem `b2c`, `ab1`
 * <hr>
 * abc -> cab -> rotate `ab` to `ba` -> cba
 */
public class SwapNonAdjacent {

    public static <T> void swapNonAdjacent(T[] a, int fs, int fl, int ss, int sl) {
        if (fs + fl > ss || ss + sl > a.length) {
            throw new IllegalArgumentException();
        }
        RotateArray.recursiveRotate(a, fs, ss + sl, new RotateArray.Rotation(sl, RotateArray
                .Direction.RIGHT));
        RotateArray.recursiveRotate(a, fs + sl, ss + sl, new RotateArray.Rotation(fl, RotateArray
                .Direction.LEFT));
    }

    public static void main(String[] args) {
        int size = 100;
        Integer[] integers = new Integer[size];
        for (int i = 0; i < size; i++) {
            integers[i] = i + 1;
        }
        swapNonAdjacent(integers, 20, 20, 50, 50);
        System.out.println(Arrays.toString(integers));
    }
}
