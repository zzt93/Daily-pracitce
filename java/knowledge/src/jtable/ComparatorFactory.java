package jtable;

import java.util.Comparator;

/**
 * Created by zzt on 3/8/15.
 */
public class ComparatorFactory {

    public static Comparator<String> lastName() {
        return new Comparator<String>() {
            public int compare(String s1, String s2) {
                String[] strings1 = s1.split("\\s");
                String[] strings2 = s2.split("\\s");
                return strings1[strings1.length - 1]
                        .compareTo(strings2[strings2.length - 1]);
            }
        };
    }
}
