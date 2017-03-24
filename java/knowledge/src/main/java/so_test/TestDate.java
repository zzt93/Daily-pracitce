package so_test;

import java.text.SimpleDateFormat;

/**
 * Created by zzt on 6/4/15.
 * <p>
 * Description:
 */
public class TestDate {
    public static void main(String[] args) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        System.out.println(sdf.format(date));
    }
}
