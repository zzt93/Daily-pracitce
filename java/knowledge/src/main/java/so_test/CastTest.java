package so_test;

/**
 * Created by zzt on 12/31/16.
 * <p>
 * <h3></h3>
 */
public class CastTest {

    public static void main(String[] args) {
        long i = (long) (Long.MAX_VALUE + 1.1);
        long i2 = Long.MAX_VALUE + 1;
        System.out.println(i);
        System.out.println(i2);
    }
}
