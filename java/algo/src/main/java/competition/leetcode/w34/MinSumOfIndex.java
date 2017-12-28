package competition.leetcode.w34;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 5/28/17.
 * <p>
 * <h3></h3>
 */
public class MinSumOfIndex {

    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<Integer, List<String>> res = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>(list1.length);
        for (int i = 0; i < list1.length; i++) {
            count.put(list1[i], i);
        }
        int max = list1.length + list2.length;
        int min = max;
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            int tmp = i + count.getOrDefault(s, max - i);
            if (tmp < min) {
                min = tmp;
            }
            List<String> orDefault = res.getOrDefault(tmp, new ArrayList<>());
            orDefault.add(s);
            res.put(tmp, orDefault);
        }
        return res.get(min).toArray(new String[0]);
    }

    public static void main(String[] args) {
        MinSumOfIndex minSumOfIndex = new MinSumOfIndex();
        System.out.println(Arrays.toString(minSumOfIndex.findRestaurant(
                new String[]{"Burger King", "Shogun", "KFC", "Tapioca Express"},
                new String[]{"KFC", "Shogun", "Burger King"})));
    }
}
