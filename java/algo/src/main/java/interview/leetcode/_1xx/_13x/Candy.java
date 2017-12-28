package interview.leetcode._1xx._13x;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 10/18/17.
 * <p>
 * <h3></h3>
 */
public class Candy {

    public int candy(int[] ratings) {
        int candies[] = new int[ratings.length];
        Arrays.fill(candies, 1);// Give each child 1 candy

        for (int i = 1; i < candies.length; i++) {// Scan from left to right, to make sure right
            // higher rated child gets 1 more candy than left lower rated child
            if (ratings[i] > ratings[i - 1]) candies[i] = (candies[i - 1] + 1);
        }

        for (int i = candies.length - 2; i >= 0; i--) {// Scan from right to left, to make sure
            // left higher rated child gets 1 more candy than right lower rated child
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
        }

        int sum = 0;
        for (int candy : candies)
            sum += candy;
        return sum;
    }

    @Deprecated
    public int greedyCandy(int[] r) {
        if (r.length <= 1) {
            return r.length;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(ints -> ints[0]));
        int l = r.length;
        for (int i = 0; i < l; i++) {
            queue.add(new int[]{r[i], i});
        }
        int[] can = new int[l];
        while (!queue.isEmpty()) {
            int[] head = queue.poll();
            int i = head[1];
            if (i == 0) {
                if (r[1] > r[0]) {
                    can[0] = 1;
                } else if (r[1] == r[0]) {
                    if (can[1] != 0) {
                        can[0] = can[1];
                    } else {
                        can[0] = 1;
                    }
                } else {
                    assert can[1] != 0;
                    can[0] = can[1] + 1;
                }
            } else if (i == l - 1) {
                if (r[i] < r[i - 1]) {
                    can[i] = 1;
                } else if (r[i] == r[i - 1]) {
                    if (can[i - 1] != 0) {
                        can[i] = can[i - 1];
                    } else {
                        can[i] = 1;
                    }
                } else {
                    assert can[i - 1] != 0;
                    can[i] = can[i - 1] + 1;
                }
            } else {
            }
        }
        return Arrays.stream(can).sum();
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
        System.out.println(candy.candy(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(candy.candy(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
        System.out.println(candy.candy(new int[]{}));
        System.out.println(candy.candy(new int[]{1, 3, 3, 2}));
        System.out.println(candy.candy(new int[]{2, 3, 3, 1}));
        System.out.println(candy.candy(new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println(candy.candy(new int[]{1}));
        System.out.println(candy.candy(new int[]{1, 2}));
        System.out.println(candy.candy(new int[]{1, 2, 1}));
    }
}
