import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 3/28/15.
 */
public class codeJamTemp {



    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int res = 0;//invoke some function
            out.println("case #" + (i+1) + ": " + res);
        }
    }
}
