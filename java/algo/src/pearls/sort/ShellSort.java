package pearls.sort;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public class ShellSort {

    private static int[] gaps = {7, 3, 1};

    public static void shell(int[] ints) {
        for (int gap : gaps) {
            gapInsertionSort(ints, gap);
        }
    }

    private static void gapInsertionSort(int[] ints, int gap) {
        int last = ints[ints.length - 1];
        int i;
        for (i = ints.length - 1; i >= gap; i -= gap) {
            if (ints[i] < ints[i - gap]) {
                ints[i] = ints[i - gap];
            } else {
                break;
            }
        }
        ints[i] = last;
    }
}
