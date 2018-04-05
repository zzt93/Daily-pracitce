package interview.leetcode._3xx._33x;

/**
 * @author zzt
 */
public class VerifyPreorder_331 {

  public static void main(String[] args) {
    VerifyPreorder_331 v = new VerifyPreorder_331();
    System.out.println(v.isValidSerialization("1,#,#,#,#"));
    System.out.println(v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    System.out.println(v.isValidSerialization("1,#,#"));
    System.out.println(v.isValidSerialization("1,#"));
    System.out.println(v.isValidSerialization(
        "9,9,9,#,9,9,9,9,9,9,#,#,9,#,#,#,#,9,9,9,9,#,9,#,9,#,#,#,#,9,9,#,9,#,#,9,9,#,#,9,9,#,9,#,#,9,#,#,9,9,9,9,#,9,#,#,9,9,#,#,9,9,9,#,#,9,#,#,9,#,#,9,#,#,#,9,9,9,9,9,9,#,9,9,9,#,#,#,#,9,#,#,9,9,#,9,#,9,#,9,9,#,#,#,9,9,9,#,9,9,9,#,#,#,9,#,#,9,9,#,9,#,#,9,9,#,#,9,#,#,9,#,#,9,9,#,9,9,#,#,#,9,9,#,#,#,9,9,9,#,9,9,#,9,#,#,#,9,#,9,#,9,#,#,9,9,#,9,#,9,#,#,9,9,#,#,9,9,#,9,9,#,#,#,9,#,9,9,#,9,#,9,#,#,#,9,9,9,9,9,9,9,#,#,#,9,9,9,#,9,#,#,#,9,#,#,#,9,9,#,#,9,9,#,#,9,9,9,9,#,#,#,9,#,#,#,9,9,#,#,#,9,9,#,#,#,9,9,9,9,9,9,#,#,9,#,9,#,#,9,#,#,9,9,9,#,#,#,9,#,9,#,#,9,9,#,9,#,9,9,9,#,#,9,#,#,#,9,#,9,#,#,9,#,9,9,#,#,9,#,#"));
  }

  public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    if (nodes.length == 0) return false;
    int leaves = 0, inner = 0;
    int i;
    for (i = 0; i < nodes.length && leaves != inner + 1; i++) {
      if (nodes[i].equals("#")) leaves++;
      else inner++;
    }
    return leaves == inner + 1 && i == nodes.length;
  }

  public boolean isValidSerializationTLE(String preorder) {
    String[] nodes = preorder.split(",");
    return isValid(nodes, 0, nodes.length);
  }

  private boolean isValid(String[] nodes, int s, int e) {
    if ((e - s) % 2 == 0) {
      return false;
    }
    boolean res = !nodes[s].equals("#");
    if (e - s == 1) {
      return !res;
    }
    if (e - s == 3) {
      return res && nodes[s + 1].equals(nodes[s + 2]) && nodes[s + 1].equals("#");
    }
    if (nodes[s + 1].equals("#")) {
      return isValid(nodes, s + 2, e);
    }
    if (res) {
      for (int i = s + 1; i < e - 1; i++) {
        if (nodes[i].equals("#") && nodes[i + 1].equals("#")) {
          res = isValid(nodes, s + 1, i + 2) && isValid(nodes, i + 2, e);
          if (res) {
            return true;
          }
        }
      }
    }
    return res;
  }

}
