package reflect;

/**
 * Created by zzt on 5/11/16.
 * <p>
 * Usage:
 */
public class HashCode {
    public static void main(String[] args) {
        Object address = new Object();
        System.out.println(address.hashCode());
        System.out.println(address);

        int loop = 1000;
        for (int i = 0; i < loop; i++) {
            long[] longs = new long[1024 * 1024];
        }
        System.gc();
        System.out.println(address.hashCode());
        System.out.println(address);
    }
}
