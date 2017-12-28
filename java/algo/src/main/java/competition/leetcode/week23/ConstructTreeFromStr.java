package competition.leetcode.week23;

/**
 * Created by zzt on 3/12/17.
 * <p>
 * <h3></h3>
 */
public class ConstructTreeFromStr {

    public TreeNode str2tree(String s) {
        return makeTree(s.toCharArray(), 0, s.length());
    }

    private TreeNode makeTree(char[] chars, int s, int e) {
        // number(...)(...)
        if (s >= e) {
            return null;
        }
        int l = find(chars, s, e, '(');
        assert l > s;
        int x = Integer.parseInt(new String(chars, s, l - s));
        final TreeNode root = new TreeNode(x);
        int split = findPair(chars, l, e);
        assert split <= e;
        // remove redundant parenthesis
        root.left = makeTree(chars, l + 1, split);
        root.right = makeTree(chars, split + 2, e - 1);
        return root;
    }

    private int findPair(char[] chars, int s, int e) {
        int c = 0;
        for (int i = s; i < e; i++) {
            if (chars[i] == '(') {
                c++;
            } else if (chars[i] == ')') {
                c--;
            }
            if (c == 0) {
                return i;
            }
        }
        return e;
    }

    private int find(char[] chars, int s, int e, char aim) {
        for (int i = s; i < e; i++) {
            if (chars[i] == aim) {
                return i;
            }
        }
        return e;
    }

    public static void main(String[] args) {
        final ConstructTreeFromStr str = new ConstructTreeFromStr();
        final TreeNode x = str.str2tree("4(2(-3(-1))(1))(6(5(-45)))");
        final TreeNode x2 = str.str2tree("4");
        System.out.println(x);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
