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
<<<<<<< HEAD
            in = new MyIn("testCase/-small-practice.in");//TODO add file name
=======
            in = new MyIn("-large-practice.in");
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
<<<<<<< HEAD
            int res = 0;//TODO invoke some function
=======
            int res = 0;//invoke some function
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
            out.println("case #" + (i+1) + ": " + res);
        }
    }
}
