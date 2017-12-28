import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 3/28/15.
 * <p/>
 * CodeJam practice template
 * -- handle input/output and basic argument conversion
 */
public class codeJamTemp {


    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/-small-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {

            res = 0;//TODO invoke some function
            out.println("Case #" + (i + 1) + ": " + res);
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }
}
