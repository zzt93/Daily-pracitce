package enum_type;

import java.util.Random;

/**
 * Created by zzt on 7/8/15.
 * <p>
 * Description:
 */
public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public static final Gender all[] = values();
    public static final int size = all.length;
    private static Random random = new Random(23);

    public static Gender getRandomGender() {
        return all[random.nextInt(size)];
    }
}
