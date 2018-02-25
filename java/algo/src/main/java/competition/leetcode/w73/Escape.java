package competition.leetcode.w73;

/**
 * @author zzt
 */
public class Escape {

  public boolean escapeGhosts(int[][] ghosts, int[] target) {
    int dis = Math.abs(target[0]) + Math.abs(target[1]);
    for (int[] ghost : ghosts) {
      if (dis >= (Math.abs(ghost[0] - target[0])) + (Math.abs(ghost[1]-target[1]))) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Escape b = new Escape();
    System.out.println(b.escapeGhosts(new int[][]{new int[]{1, 0}, new int[]{0, 3}}, new int[]{0, 1}));
    System.out.println(b.escapeGhosts(new int[][]{new int[]{1, 0}}, new int[]{2, 0}));
    System.out.println(b.escapeGhosts(new int[][]{new int[]{2, 0}}, new int[]{1, 0}));
    System.out.println(b.escapeGhosts(new int[][]{new int[]{1,9},new int[]{2,-5},new int[]{3,8},new int[]{9,8},new int[]{-1,3}}, new int[]{8, -10}));
  }
}
