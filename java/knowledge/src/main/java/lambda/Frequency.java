package lambda;

import java.util.HashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by zzt on 4/20/16.
 * <p>
 * Usage:
 */
public class Frequency {
    private HashMap<String, LongAdder> freqs
            = new HashMap<>();

    public void count(String s) {
        freqs.putIfAbsent(s, new LongAdder()).increment();
//        freqs.computeIfAbsent(s, key -> new LongAdder()).increment();
    }

    public long num(String s) {
        return freqs.get(s).longValue();
    }
}
