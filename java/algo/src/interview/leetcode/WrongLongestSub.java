package interview.leetcode;

/**
 * Created by zzt on 4/11/16.
 * <p>
 * Usage:
 * find longest palindromic string by finding the longest common sub-string
 */
@Deprecated
public class WrongLongestSub {

    class Res {
        int count;
        StringBuilder sb = new StringBuilder();

        public Res append(char c) {
            count++;
            sb.append(c);
            return this;
        }

        public String getString() {
            return sb.toString();
        }
    }

    /**
     * plain way: String f, s; find s[0] in f, then try find s[1] in adjacent position;
     * if fail, find s[1] in f, find s[2] in adjacent position ...
     */
    private Res mylcs(String f, int fs, String s) {
        char ch = f.charAt(fs);
        int first = s.indexOf(ch);
        Res tmp = new Res();
        if (first < 0) {
            return tmp;
        }
        Res max = new Res();
        for (int i = first; i < s.length() && fs < f.length(); i++) {
            char sc = s.charAt(i);
            char fc = f.charAt(fs++);
            if (sc == fc) {
                tmp.append(sc);
            } else {
                i = s.indexOf(ch, first + 1);
                if (max.count < tmp.count) {
                    max = tmp;
                }
                tmp = new Res();
                if (i < 0) {
                    return max;
                }
            }
        }
        if (max.count < tmp.count) {
            max = tmp;
        }
        return max;
    }

    private String myLCS(String f, String s) {
        int fSize = f.length();
        Res max = new Res();
        for (int i = 0; i < fSize; i++) {
            Res tmp = mylcs(f, i, s);
            if (max.count < tmp.count) {
                max = tmp;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            Res tmp = mylcs(s, i, f);
            if (max.count < tmp.count) {
                max = tmp;
            }
        }
        return max.getString();
    }

    /**
     * directly using longest common substring is incorrect: abcdba
     * <p></p>
     * To rectify this, each time we find a longest common substring candidate,
     * we check if the substring’s indices are the same
     * as the reversed substring’s original indices, i.e.
     * for string [i, j] and reversed [ii, jj]
     * i + jj == len && j + ii == len
     */
    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return myLCS(s, sb.reverse().toString());
    }

}
