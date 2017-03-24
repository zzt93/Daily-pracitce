package so_test;

/**
 * Created by zzt on 6/19/15.
 * <p>
 * Description:
 */
public class Forwad {

    private String testString1;

    {
        testString1 = this.testString2;
    }

    private String testString2;

    public static void main(String args[]) {
        new Forwad();
    }
}
