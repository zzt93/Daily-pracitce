package lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zzt on 5/25/15.
 * <p>
 * Description:
 */
public class ComparatorFa {
    public static Comparator<String> comparator1() {
        return (o1, o2) -> {
            Double d1 = Double.parseDouble(o1);
            Double d2 = Double.parseDouble(o2);
            return d1.compareTo(d2);
        };
    }

    public static Comparator<String> comparator2() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Double d1 = Double.parseDouble(o1);
                Double d2 = Double.parseDouble(o2);
                return d1.compareTo(d2);
            }
        };
    }

    public static Comparator<String> comparator3() {
        return (o1, o2) -> {
//            if (true) {
//                throw new RuntimeException("Exception");
//            }
            Double d1 = Double.parseDouble(o1);
            Double d2 = Double.parseDouble(o2);
            return d1.compareTo(d2);
        };
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        list.add("1.1");
        list.add("1.2");
        Collections.sort(list, comparator1());

    }
}
