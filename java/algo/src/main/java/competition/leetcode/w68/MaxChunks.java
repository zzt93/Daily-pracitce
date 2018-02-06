package competition.leetcode.w68;

import java.util.Random;

/**
 * Created by zzt on 1/21/18.
 * <p>
 * <h3></h3>
 */
public class MaxChunks {

    public int maxChunksToSorted(int[] arr) {
        int c = 0;
        int end = 0;
        int i;
        for (i = 0; i < arr.length; i++) {
            int aim = arr[i];
            boolean small = false;
            for (int x = i + 1; x < arr.length; x++) {
                if (aim > arr[x]) {
                    small = true;
                    end = Math.max(x, end);
                }
            }
            if (!small) {
                end = Math.max(i, end);
            }
            if (i >= end) {
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        MaxChunks f = new MaxChunks();
        System.out.println(f.maxChunksToSorted(new int[]{0, 4, 5, 2, 1, 3}));
        System.out.println(f.maxChunksToSorted(new int[]{1}));
        System.out.println(f.maxChunksToSorted(new int[]{4, 3, 2, 1, 0}));
        System.out.println(f.maxChunksToSorted(new int[]{1, 4, 0, 2, 3, 5}));
        System.out.println(f.maxChunksToSorted(new int[]{1, 2, 3, 4}));
        System.out.println(f.maxChunksToSorted(new int[]{1, 2, 3, 3, 3, 4}));
        System.out.println(f.maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
        int[] ints = new Random(12).ints(2000, 0, 100000000).toArray();
        System.out.println(f.maxChunksToSorted(ints));
    }
}
