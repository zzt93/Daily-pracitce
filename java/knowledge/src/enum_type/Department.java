package enum_type;

import java.util.Random;

/**
 * Created by zzt on 7/8/15.
 * <p>
 * Description:
 */
public enum Department {
    SALES, SUPPORT, TECH, DESIGN;

    public static final Department values[] = values();
    public static final int size = values.length;
    private static Random random;

    public static Department getRandomDepartment() {
        random = new Random();
        return values[random.nextInt(size)];
    }
}
