package competition.leetcode.w31;

import java.util.HashSet;

/**
 * Created by zzt on 5/7/17.
 * <p>
 * <h3></h3>
 */
public class DistributeCandies {

    public int distributeCandies(int[] candies) {
        HashSet<Integer> integers = new HashSet<>();
        for (int candy : candies) {
            integers.add(candy);
        }
        return integers.size() * 2 > candies.length ? candies.length / 2 : integers.size();
    }

    public static void main(String[] args) {
        DistributeCandies distributeCandies = new DistributeCandies();
        System.out.println(distributeCandies.distributeCandies(new int[]{1, 2, 3, 4}));
        System.out.println(distributeCandies.distributeCandies(new int[]{1, 2, 1, 1, 1, 1}));
    }
}
