package methodology.dynamic;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Usage:
 */
public class LCSubstring {

    public int LCSuffix(String s1, int end, String s2, int end2) {
        if (end == 0 || end2 == 0) {
            return 0;
        }
        if (s1.charAt(end - 1) == s2.charAt(end2 - 1)) {
            return 1 + LCSuffix(s1, end - 1, s2, end2 - 1);
        }
        return 0;
    }

    public int LCSub(String s1, String s2) {
        int all = s1.length() + s2.length();
        int maxPossible = Math.min(s1.length(), s2.length());
        int max = 0;
        String sub = "";
        OUT:
        for (int i = all; i > 2; i--) {
            ArrayList<int[]> lens = splitSum(i, s1.length(), s2.length());
            for (int[] len : lens) {
                int minLen = Math.min(len[0], len[1]);
                if (max > minLen) {
                    continue;
                }
                int res = LCSuffix(s1, len[0], s2, len[1]);
                if (res == maxPossible) {
                    sub = s1.substring(len[0] - res, len[0]);
                    max = res;
                    break OUT;
                }
                if (res > max) {
                    max = res;
                    sub = s1.substring(len[0] - res, len[0]);
                    if (res == i / 2) {
                        break OUT;
                    }
                }
            }
        }
        System.out.println("SubString: " + sub);
        return max;
    }

    private ArrayList<int[]> splitSum(int sum, int limit1, int limit2) {
        ArrayList<int[]> res = new ArrayList<>();
        if (limit1 < limit2) {
            if (sum <= limit1 * 2) {
                splitSymmetry(sum, limit1, res);
            }
            if (sum > limit1) {
                splitUpper(sum, limit1, limit2, res, (i, j) -> new int[]{i, j});
            }
        } else {
            if (sum <= limit2 * 2) {
                splitSymmetry(sum, limit2, res);
            }
            if (sum > limit1) {
                splitUpper(sum, limit2, limit1, res, (i, j) -> new int[]{j, i});
            }
        }
        return res;
    }

    private void splitUpper(int sum, int start, int upperBound, ArrayList<int[]> res,
                            BiFunction<Integer, Integer, int[]> supplier) {
        for (int i = upperBound; i > start && sum - i <= start; i--) {
            res.add(supplier.apply(sum - i, i));
        }
    }

    private void splitSymmetry(int sum, int min, ArrayList<int[]> res) {
        int ave = sum / 2;
        if (sum % 2 == 0) {
            res.add(new int[]{ave, ave});
        }
        for (int i = ave + 1; i <= min; i++) {
            int j = sum - i;
            if (j == 0) {
                return;
            }
            res.add(new int[]{i, j});
            res.add(new int[]{j, i});
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("ambabac");
        System.out.println(new LCSubstring().LCSub(sb.toString(), sb.reverse().toString()));
    }
}