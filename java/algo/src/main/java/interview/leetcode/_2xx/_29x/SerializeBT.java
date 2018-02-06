package interview.leetcode._2xx._29x;

import interview.leetcode.TreeNode;
import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * @author zzt
 */
public class SerializeBT {

  public static void main(String[] args) {
    SerializeBT s = new SerializeBT();
    System.out.println(s.serialize(s.deserialize("[1,2,3,null,null,4,5]")));
    System.out.println(s.serialize(s.deserialize("[1,2,3,null,null,4,5,6,7]")));
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringJoiner sb = new StringJoiner(",", "[", "]");
    LinkedList<TreeNode> father = new LinkedList<>();
    LinkedList<TreeNode> child = new LinkedList<>();
    int num = 2;
    boolean hasNum = false;
    father.add(root);
    while (!father.isEmpty() || !child.isEmpty()) {
      TreeNode poll = father.poll();
      if (child.size() < num) {
        if (poll != null) {
          sb.add("" + poll.val);
          child.add(poll.left);
          if (poll.left != null) {
            hasNum = true;
          }
          child.add(poll.right);
          if (poll.right != null) {
            hasNum = true;
          }
        } else {
          sb.add("null");
          num -= 2;
        }
      } else {
        if (hasNum) {
          father.addAll(child);
          num *= 2;
        }
        hasNum = false;
        child.clear();
      }
    }
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] split = data.substring(1, data.length() - 1).split(",");
    if (split.length == 1) {
      if (split[0].equals("null")) {
        return null;
      } else {
        return new TreeNode(Integer.parseInt(split[0]));
      }
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(split[0]));
    queue.add(root);
    int i = 1;
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      if (i < split.length) {
        if (split[i].equals("null")) {
          poll.left = null;
        } else {
          TreeNode left = new TreeNode(Integer.parseInt(split[i]));
          poll.left = left;
          queue.add(left);
        }
        i++;
      }
      if (i < split.length) {
        if (split[i].equals("null")) {
          poll.right = null;
        } else {
          TreeNode right = new TreeNode(Integer.parseInt(split[i]));
          poll.right = right;
          queue.add(right);
        }
        i++;
      }
    }
    return root;
  }

}
