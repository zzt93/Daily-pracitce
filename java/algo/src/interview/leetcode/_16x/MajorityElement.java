package interview.leetcode._16x;

/**
 * Created by zzt on 11/14/17.
 * <p>
 * <h3></h3>
 */
public class MajorityElement {

    public int majorityElement(int[] n) {
        int m = n[0];
        int c = 1;
        for (int i = 1; i < n.length; i++) {
            if (m == n[i]) {
                c++;
            } else {
                c--;
                if (c == 0) {
                    c = 1;
                    m = n[i];
                }
            }
        }
        return m;
    }

    public static void main(String[] args) {
        MajorityElement m = new MajorityElement();
        System.out.println(m.majorityElement(new int[]{1}));
        System.out.println(m.majorityElement(new int[]{1,1,2}));
        System.out.println(m.majorityElement(new int[]{1,1,2,2,2}));
        System.out.println(m.majorityElement(new int[]{1,1,3,2,2,2,2}));
    }
}
