package memory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zzt on 4/23/16.
 * <p>
 * Usage:
 */
public class LargeClass {
    static final byte[] moreBytesToLeak = new byte[1024 * 1024 * 10];

    public static void main(String[] args) throws IOException {
        System.in.read();
        ConcurrentHashMap<Integer, Integer> tmp = new ConcurrentHashMap<>();
        tmp.replace(1, 1, 1);
    }
}
