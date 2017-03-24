package enum_type;

import java.util.Random;

/**
 * Created by zzt on 7/8/15.
 * <p>
 * Description:
 */
public enum Department {
    SALES, SUPPORT, TECH, DESIGN;

    public static final Department all[] = values();
    public static final int size = all.length;
    private static Random random = new Random(23);

    public static Department getRandomDepartment() {
        return all[random.nextInt(size)];
    }
}
