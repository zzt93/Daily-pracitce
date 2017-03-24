package inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 7/14/16.
 * <p>
 * <h3></h3>
 */
public class InitializationBlock {

    public static void main(String[] args) {
        List<String> list3 = new ArrayList<String>() {

            // initialization block for anonymous class
            {
                add("s1");
                add("s2");
                add("s3");
            }
        };

        System.out.println(list3.getClass());
    }
}
