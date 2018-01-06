package competition.leetcode.w65;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zzt on 1/1/18.
 * <p>
 * <h3></h3>
 */
public class PyramidTransition {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        boolean[][][] map = new boolean[7][7][7];
        for (String s : allowed) {
            map[s.charAt(0) - 'A'][s.charAt(1) - 'A'][s.charAt(2) - 'A'] = true;
        }
        int l = bottom.length();
        boolean[][][] dp = new boolean[l][l][7];
        char[] cs = bottom.toCharArray();
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < 7; j++) {
                if (map[cs[i] - 'A'][cs[i + 1] - 'A'][j]) {
                    dp[i][i + 1][j] = true;
                }
            }
        }
        for (int g = 1; g < l - 1; g++) {
            for (int s = 0; s + g + 1 < l; s++) {
                boolean[] fHead = dp[s][s + g];
                for (int i = 0; i < fHead.length; i++) {
                    if (fHead[i]) {
                        boolean[] sHead = dp[s + 1][s + 1 + g];
                        for (int j = 0; j < sHead.length; j++) {
                            if (sHead[j]) {
                                for (int k = 0; k < map[i][j].length; k++) {
                                    if (map[i][j][k]) dp[s][s+g + 1][k] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (boolean b : dp[0][l - 1]) {
            if (b) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PyramidTransition p = new PyramidTransition();
        //        System.out.println(p.pyramidTransition("", Lists.newArrayList()));
        System.out.println(p.pyramidTransition("ABCBA", Lists.newArrayList("ABD", "BCE", "CBE",
                "BAD", "DEF", "FFF", "EDF", "EEF")));
        System.out.println(p.pyramidTransition("ABC", Lists.newArrayList("ABD", "BCE", "DEF",
                "FFF")));
        System.out.println(p.pyramidTransition("ABCBA", Lists.newArrayList("ABD", "BCE", "DEF",
                "FFF")));
        System.out.println(p.pyramidTransition("ABCBA", Lists.newArrayList("ABD", "BCE", "DEF",
                "FFF", "EDF")));
    }

}
