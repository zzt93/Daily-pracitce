package interview.leetcode._3xx._30x;

import java.util.List;

/**
 * @author zzt
 * <h3>Simulate: BFS, DFS</h3>
 */
public class RemoveInvalidParen {

  public static void main(String[] args) {
    RemoveInvalidParen r = new RemoveInvalidParen();
    System.out.println(r.removeInvalidParentheses("()(()(())((()"));
    System.out.println(r.removeInvalidParentheses("()())(())((()"));
    System.out.println(r.removeInvalidParentheses(")()("));
    System.out.println(r.removeInvalidParentheses("()("));
    System.out.println(r.removeInvalidParentheses("))"));
    System.out.println(r.removeInvalidParentheses("(())"));
    System.out.println(r.removeInvalidParentheses("(()"));
    System.out.println(r.removeInvalidParentheses("())("));
    System.out.println(r.removeInvalidParentheses("(()(()"));
    System.out.println(r.removeInvalidParentheses("(a)())()"));
  }

  private List<String> removeInvalidParentheses(String s) {
    return null;
  }

}
