package enum_type;

import java.util.Random;

/**
 * Created by zzt on 7/8/15.
 * <p>
 * Description:
 */
public enum Department {
    SALES, SUPPORT, TECH, DESIGN;

    public static Department getRandomDepartment() {
        return Department.values()[new Random().nextInt(4)];
    }
}
