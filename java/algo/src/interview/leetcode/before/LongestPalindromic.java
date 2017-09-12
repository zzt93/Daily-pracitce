package interview.leetcode.before;

import competition.utility.MyIn;

import java.util.ArrayList;

/**
 * Created by zzt on 7/6/16.
 * <p>
 * <h3></h3>
 *
 * @see WrongLongestPalindromic
 * @see WrongLongestSub
 */
public class LongestPalindromic {


    private static class PalindromicCenter {
        // not include '.'
        private boolean canExtend;
        private int maxLen;
        private int i;

        PalindromicCenter(int maxLen, int i) {
            this.canExtend = true;
            this.maxLen = maxLen;
            this.i = i;
        }
    }

    /**
     * <h3>Dynamic equation</h3>
     * longestCenter[n-1] + 2/1 = longestCenter[n] if new char is the same with
     * symmetric char
     *
     * @return if multiple string with same max len, return the first string
     */
    public static String lpDynamic(String string) {
        if (string.isEmpty()) {
            return "";
        }
        if (string.length() == 1) {
            return string;
        }

        int len = string.length();
        final ArrayList<PalindromicCenter> centers = new ArrayList<>(len * 2 - 1);
        final char[] toCharArray = string.toCharArray();
        {
            int j = 0;
            for (int in = 0; in < toCharArray.length - 1; in++) {
                centers.add(new PalindromicCenter(1, j++));
                centers.add(new PalindromicCenter(0, j++));
            }
            centers.add(new PalindromicCenter(1, j));
        }

        final int size = centers.size();
        int maxCenterI = 0;
        int max = 0;
        boolean canExtend;
        do {
            canExtend = false;
            for (int i = 0; i < size; i++) {
                final PalindromicCenter center = centers.get(i);
                if (center.canExtend && extendCenter(center, i, toCharArray, len)) {
                    canExtend = true;
                    if (center.maxLen > max) {
                        max = center.maxLen;
                        maxCenterI = i;
                    }
                } else {
                    center.canExtend = false;
                }
            }
        } while (canExtend);


        final PalindromicCenter first = centers.get(maxCenterI);
        StringBuilder sb = new StringBuilder(first.maxLen);
        int dis = disToCenter(first);
        int start = noDotArrayIndex(first.i - dis) + 1;
        int end = noDotArrayIndex(first.i + dis);
        while (start < end) {
            sb.append(toCharArray[start]);
            start++;
        }
        return sb.toString();
    }

    private static int disToCenter(PalindromicCenter first) {
        return first.maxLen + 1;
    }

    private static boolean extendCenter(PalindromicCenter center, int centerI, char[] chars, int
            end) {
        int dis = disToCenter(center);
        int left = noDotArrayIndex(centerI - dis);
        int right = noDotArrayIndex(centerI + dis);
        if (left >= 0 && right < end) {
            if (chars[left] == chars[right]) {
                center.maxLen += 2;
                return true;
            }
        }
        return false;
    }

    /**
     * @param i Index in extended array which has '.' in it
     *
     * @return the index of a character in the original array
     */
    private static int noDotArrayIndex(int i) {
        if ((i & 1) == 1) {
            assert false;
        }
        return i / 2;
    }


    public static void main(String[] args) {
        MyIn in = new MyIn();
        //        in.next();
        final WrongLongestSub wrongLongestSub = new WrongLongestSub();
        output(wrongLongestSub, "ccc");
        output(wrongLongestSub, "cccc");
        output(wrongLongestSub, "ccccc");
        output(wrongLongestSub, "abcbcb");
        output(wrongLongestSub, "abcbac");
        output(wrongLongestSub,
                "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa");
        //        in.next();
        //        for (int i = 0; i < 10; i++) {
        //            final String s = RandomStr.sameSeed(10);
        //            output(wrongLongestSub, s);
        //        }
    }

    private static void output(WrongLongestSub wrongLongestSub, String s) {
        System.out.print(s + ": ");
        System.out.print(lpDynamic(s) + ": ");
        System.out.println(wrongLongestSub.longestPalindrome(s));
    }
}
