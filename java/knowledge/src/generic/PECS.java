package generic;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 7/6/15.
 * <p>
 * Description: Tey to understand 'PESC' by code and example
 */
public class PECS {

    public <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (T t : src) {
            dest.add(t);
        }
    }

    public <T> List<T> getNew(List<? extends T> src) {
        return src.stream()
                .collect(Collectors.toList());
    }

}
