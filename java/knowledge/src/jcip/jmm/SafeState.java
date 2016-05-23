package jcip.jmm;

import annotation.ThreadSafe;

import java.util.HashMap;

/**
 * Created by zzt on 5/23/16.
 * <p>
 * <p>Initialization safety means that {@link SafeState} could be safely
 * published even through unsafe lazy initialization or stashing a reference to a
 * {@link SafeState} in a public static field with no synchronization, even though it uses no
 * synchronization and relies on the non-thread-safe HashMap .</p>
 * <p>
 * However, a number of small changes to {@link SafeState} would take away its
 * thread safety. If states were not final, or if any method other than the constructor
 * modified its contents, initialization safety would not be strong enough to safely
 * access SafeStates without synchronization.
 * </p>
 */
@ThreadSafe
public class SafeState {

    private final HashMap<String, Integer> map;

    public SafeState() {
        map = new HashMap<String, Integer>();
        map.put("1", 1);
        map.put("2", 2);
    }

    public int getInt(String num) {
        return map.get(num);
    }
}
