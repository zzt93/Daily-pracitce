package suffixTree;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 */
public class LCSubString {
    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        StringBuilder sb = new StringBuilder("bacab");
        suffixTree.add(sb.toString());
        suffixTree.add(sb.reverse().toString());
        // find a deepest node which have both end symbol
    }
}
