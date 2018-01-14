package competition.leetcode.w67;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 1/14/18.
 * <p>
 * <h3></h3>
 */
public class PartitionLabels {

    public static final int FIND = 0;
    public static final int IN = 1;

    public List<Integer> partitionLabels(String s) {
        char[] cs = s.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            map[c-'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int state = FIND;
        int end = 0, start = 0;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            switch (state){
                case FIND:
                    if (map[c-'a'] > i) {
                        state = IN;
                        start = i;
                        end = map[c-'a'];
                    } else {
                        res.add(1);
                    }
                    break;
                case IN:
                    end = Math.max(end, map[c-'a']);
                    if (i == end) {
                        state = FIND;
                        res.add(end-start+1);
                    }
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PartitionLabels p = new PartitionLabels();
        System.out.println(p.partitionLabels("a"));
        System.out.println(p.partitionLabels("ababcbacadefegdehijhklij"));
    }

}
