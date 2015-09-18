package practice.round1A2008.qulification;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 4/11/15.
 * <p/>
 * Description:
 */
public class ovation {

    public static int ovation(String list) {
        int friend = 0;
        int stand = list.charAt(0) - '0';
        for (int i = 1; i < list.length(); i++) {
            int thislevel = list.charAt(i) - '0';
            if (stand < i) {
                friend = friend + (i - stand);
                stand = i;
            }
            stand += thislevel;
        }
        return friend;
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/ovation-large.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            in.next();
            int res = ovation(in.next());
            out.println("case #" + (i+1) + ": " + res);
        }
    }
}
