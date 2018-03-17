package competition.leetcode.w75;

/**
 * @author zzt
 */
public class SmallestRotation {

    public int bestRotationTLE(int[] a) {
        int l = a.length;
        int[] map = new int[l];
        for (int i = l - 1; i >= 0; i--) {
            int offset = l - 1 - i;
            //            for (int x = l - 1, k = l - 1 - x - offset; x >= a[i]; x--, k++) {
            //                map[k < 0 ? k + l : k]++;
            //            }
            int x = l - 1;
            for (int k = l - offset; x >= a[i] && k < l; x--, k++) {
                map[k]++;
            }
            for (int k = 0; x >= a[i] && k < l - offset; x--, k++) {
                map[k]++;
            }

        }
        int max = 0, maxI = 0;
        for (int i = 0; i < l; i++) {
            if (map[i] > max) {
                maxI = i;
                max = map[i];
            }
        }
        return maxI;
    }

    /**
     * rangeMap[range, int]
     */
    public int bestRotation(int[] A) {
        return 0;
    }

    public static void main(String[] args) {
        SmallestRotation s = new SmallestRotation();
        System.out.println(s.bestRotation(new int[]{2, 3, 1, 4, 0}));
        System.out.println(s.bestRotation(new int[]{1, 3, 0, 2, 4}));
    }

}
