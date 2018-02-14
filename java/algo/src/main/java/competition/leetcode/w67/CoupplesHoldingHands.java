package competition.leetcode.w67;


import java.util.HashMap;

/**
 * Created by zzt on 1/17/18.
 * <p>
 * <h3></h3>
 */
public class CoupplesHoldingHands {

    public int minSwapsCouples(int[] row) {
        HashMap<Integer,Integer> coupleIndex = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            int c = row[i];
            coupleIndex.put(c, i);
        }
        int res = 0;
        for (int i = 0; i < row.length; i++) {
            int c = row[i];
            int couple = c % 2 == 0 ? c + 1 : c - 1;
            Integer ci = coupleIndex.get(couple);
            int t = row[ci];
            if (i%2==0) {
                if (ci==i+1) {
                    i++;
                    continue;
                }
                res++;
                row[ci]=row[i+1];
                row[i+1]=t;
                coupleIndex.put(row[ci],ci);
                i++;
            } else {
                if (ci==i-1)continue;
                res++;
                row[ci]=row[i-1];
                row[i-1]=t;
                coupleIndex.put(row[ci],ci);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CoupplesHoldingHands c = new CoupplesHoldingHands();
//        System.out.println(c.minSwapsCouples(new int[]{0, 2, 1, 3}));
//        System.out.println(c.minSwapsCouples(new int[]{3, 2,0,1}));
//        System.out.println(c.minSwapsCouples(new int[]{0,2,3,4,5,1}));
//        System.out.println(c.minSwapsCouples(new int[]{0,2,3,4,5,6,7,1}));
        System.out.println(c.minSwapsCouples(new int[]{0,2,3,8,4,10,11,5,9,6,7,1}));
    }
}
