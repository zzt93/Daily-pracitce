package competition.leetcode.w51;

import java.util.HashSet;

/**
 * Created by zzt on 9/24/17.
 * <p>
 * <h3></h3>
 */
public class NextClosestTime {

    public String nextClosestTime(String time) {
        HashSet<Character> set = new HashSet<>();
        for (char c : time.toCharArray()) {
            set.add(c);
        }
        String[] split = time.split(":");
        int h = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        while (true) {
            m += 1;
            if (m == 60) {
                h += 1;
                m = 0;
            }
            if (h == 24) {
                h = 0;
            }
            String s = String.format("%02d:%02d", h, m);
            boolean suc = true;
            for (char c : s.toCharArray()) {
                if (!set.contains(c)) {
                    suc = false;
                }
            }
            if (suc) {
                return s;
            }
        }
    }

    public static void main(String[] args) {
        NextClosestTime time = new NextClosestTime();
        System.out.println(time.nextClosestTime("14:44"));
        System.out.println(time.nextClosestTime("14:43"));
    }
}
