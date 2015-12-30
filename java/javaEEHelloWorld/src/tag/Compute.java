package tag;

import org.jetbrains.annotations.Contract;

import java.util.Calendar;

/**
 * Created by zzt on 12/29/15.
 * <p>
 * Usage:
 */
public class Compute {
    public static int add(String x, String y) {
        if (checkInput(x) || checkInput(y)) {
            return 0;
        }
        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(x);
            b = Integer.parseInt(y);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return a + b;
    }

    public static int computeAge(String birthYear) {
        if (checkInput(birthYear)) return 0;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int by = 0;
        try {
            by = Integer.parseInt(birthYear);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return year - by;
    }

    @Contract(value = "null -> true", pure = true)
    private static boolean checkInput(String birthYear) {
        return birthYear == null || birthYear.isEmpty();
    }


}
