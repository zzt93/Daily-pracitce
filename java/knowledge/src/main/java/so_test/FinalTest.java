package so_test;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class FinalTest {


    public void t() {
        byte f = 1;
        byte ff = (byte) (f + f);
        final byte s = 127;
        byte ss = (byte) (s + s);
        final byte s2 = 3;
        byte ss2 = s2 + s2;
    }
}
