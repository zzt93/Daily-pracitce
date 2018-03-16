package interview.google.course;

import java.util.Random;

/**
 * Created by zzt on 3/16/18.
 * <p>
 * <h3></h3>
 */
public class Logic {

    /**
     * http://codingbat.com/prob/p183562
     */
    public boolean makeBricks(int small, int big, int goal) {
        int d = (goal) / 5;
        if (big < d) return goal - 5 * big <= small;
        else return goal - 5 * d <= small;
    }

    public static void sample(int m, int n) {
        Random random = new Random();
        int select = m, remaining = n;
        for (int i = 0; i < n; i++) {
            if (random.nextInt(Integer.MAX_VALUE) % remaining < select) {
                System.out.println(i);
                select--;
            }
            remaining--;
        }
    }

    public static void sampleWithSet(int m, int n) {
    }


    public static void sampleWithShuffle(int m, int n) {
    }

}
