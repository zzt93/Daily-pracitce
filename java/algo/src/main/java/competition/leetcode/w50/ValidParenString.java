package competition.leetcode.w50;

/**
 * Created by zzt on 9/17/17.
 * <p>
 * <h3></h3>
 */
public class ValidParenString {

    public boolean checkValidString(String s) {
        int lc = 0, rc = 0, starC = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                lc++;
            } else if (c == ')') {
                rc++;
            } else if (c == '*') {
                starC++;
            }
            if (lc < rc) {
                int gap = rc - lc;
                if (starC < gap) {
                    return false;
                } else {
                    starC -= gap;
                    lc += gap;
                }
            }
        }
        int rlc = 0, rrc = 0, rStarC = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == '(') {
                rlc++;
            } else if (c == ')') {
                rrc++;
            } else if (c == '*') {
                rStarC++;
            }
            if (rlc > rrc) {
                int gap = rlc - rrc;
                if (rStarC < gap) {
                    return false;
                } else {
                    rStarC -= gap;
                    rrc += gap;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidParenString valid = new ValidParenString();
        System.out.println(valid.checkValidString("((()))()(())(*()()())**(())()()()()((*()*))((*()*)"));
//        System.out.println(valid.checkValidString(""));
//        System.out.println(valid.checkValidString("(*))***)"));
        System.out.println(valid.checkValidString("(*)))***)"));
//        System.out.println(valid.checkValidString("(())((())()()(*)(*()(())())())()()((()())((())" +
//                ")(*"));
        System.out.println(valid.checkValidString("((*)(*))((*"));
//        System.out.println(valid.checkValidString("()()"));
//        System.out.println(valid.checkValidString("()*)"));
//        System.out.println(valid.checkValidString("(**)"));
//        System.out.println(valid.checkValidString("(*))"));
//        System.out.println(valid.checkValidString("(***))"));
//        System.out.println(valid.checkValidString("())***)"));
    }
}
