package thread;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zzt on 4/20/15.
 * <p>
 * Description: try using synchronized collections
 */
public class Synchronized {

    final AtomicReference<ArrayList<Integer>> integers = new AtomicReference<>(new ArrayList<>());



    public void test () {
        AtomicReference<ArrayList<Integer>> src = integers;
        src.getAndUpdate(integers1 -> {
            integers1.add(1);
            return integers1;
        });
        ArrayList<Integer> tmp = src.get();
        tmp.add(1);
        src.set(tmp);
    }


}
