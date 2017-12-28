package competition.utility;

import java.util.Random;

/**
 * Created by zzt on 7/6/16.
 * <p>
 * <h3></h3>
 */
public class RandomStr {
    private static Random sameRandom = new Random(34);

    private Random random = new Random(34);

    public RandomStr(long seed) {
        random.setSeed(seed);
    }

    public String randomString(int len) {
        return produce(random, len);
    }

    private static String produce(Random random, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    public static String sameSeed(int len) {
        return produce(sameRandom, len);
    }
}
