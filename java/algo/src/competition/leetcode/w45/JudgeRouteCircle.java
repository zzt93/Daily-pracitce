package competition.leetcode.w45;

/**
 * Created by zzt on 8/13/17.
 * <p>
 * <h3></h3>
 */
public class JudgeRouteCircle {

    public boolean judgeCircle(String moves) {
        int upper = 0;
        int left = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    upper++;
                    break;
                case 'D':
                    upper--;
                    break;
                case 'L':
                    left++;
                    break;
                case 'R':
                    left--;
                    break;
            }
        }
        return upper == 0 && left == 0;
    }
}
