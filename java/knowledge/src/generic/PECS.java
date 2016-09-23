package generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 7/6/15.
 * <p>
 * Description: Tey to understand 'PESC' by code and example
 */
public class PECS {

    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (T t : src) {
            dest.add(t);
        }
    }

    //TODO why this doesn't work
    public <T> List<T> getNew(List<? extends T> src) {
        return src.stream()
                .collect(Collectors.toList());
    }

    /**
     * This is illustration of signature of sort method
     * @param src -- the original list "&lt;T&gt;"
     * @param comparator -- the comparator "<? super T>"
     * @param <T> -- the type of list
     */
    public static <T> void collectionSort(List<T> src, Comparator<? super T> comparator) {
         Collections.sort(src, comparator);
    }

    /**
     * This the illustration why super T is also work:
     * eg. list of integer can use the comparator for number
     */
    public void using_sort() {
        final ArrayList<Integer> list = new ArrayList<>();
        Collections.sort(list, new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                final Integer i1 = o1.intValue();
                final Integer i2 = o2.intValue();
                return i1.compareTo(i2);
            }
        });
    }

}
