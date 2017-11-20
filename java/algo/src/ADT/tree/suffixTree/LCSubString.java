package ADT.tree.suffixTree;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 */
public class LCSubString {
    public static void main(String[] args) {
        String src = "src";
        //        lcs1(src);
        final String res = lcs2(src);
        if (src.indexOf(res) != src.lastIndexOf(res)) {
            System.out.println("not palindrome");
        }
        System.out.println(res);

    }

    private static String lcs2(String input) {
        SuffixTree suffixTree = new SuffixTree();
        StringBuilder sb = new StringBuilder(input);
        StringBuilder append = sb.append(sb.reverse().toString());
        suffixTree.add(append.toString());
        return suffixTree.longestRepeat();
    }

    /**
     * find a deepest node which have both end symbol
     *
     * @param input
     *
     * @return longest common substring
     */
    private static String lcs1(String input) {
        SuffixTree suffixTree = new SuffixTree();
        StringBuilder sb = new StringBuilder(input);
        suffixTree.add(sb.toString());
        suffixTree.add(sb.reverse().toString());
        return null;
    }
}
