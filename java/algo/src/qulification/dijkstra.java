package qulification;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by zzt on 4/11/15.
 * <p/>
 * Description:
 */
public class dijkstra {

    private static HashMap<Chars, String> map = new HashMap<Chars, String>(16);

    public static boolean dijkstra(String s, int times) {
        StringBuilder builder = new StringBuilder(s.length()*times);
        for (int i = 0; i < times; i++) {
            builder.append(s);
        }

        char aim = 'k' + 1;
        int lasti = -1;
        String forNow = "" + builder.charAt(0);
        while (aim > 'k') {

            // re-initialize
            aim = 'i';
            for (int i = lasti + 2; i < builder.length(); i++) {
                String t = "" + builder.charAt(i);
                if (forNow.charAt(0) == aim) {
                    if (aim == 'i') {
                        lasti = i - 1;
                    }

                    aim++;
                    forNow = t;

                    if (aim > 'k') {
                        break;
                    }
                    continue;
                }
                forNow = minusCheck(multiply(forNow, t));
            }
            if (aim < 'k') {
                return false;
            } else if (aim == 'k') {
                return true;
            }

            forNow = minusCheck(multiply("i", ""+builder.charAt(lasti+1)));
        }
        return false;
    }

    private static String minusCheck(String multiply) {
        if (multiply.charAt(0) == '-' && multiply.charAt(1) == '-') {
            return multiply.substring(2);
        }
        return multiply;
    }

    private static String multiply(String forNow, String t) {
        char f0 = forNow.charAt(0);
        char t0 = t.charAt(0);
        if (f0 == '-') {
            if (t0 == '-') {
                return map.get(new Chars(forNow.substring(1), t.substring(1)));
            } else {
                return "-" + map.get(new Chars(forNow.substring(1), t));
            }
        } else {
            if (t0 != '-') {
                return map.get(new Chars(forNow, t));
            } else {
                return "-" + map.get(new Chars(forNow, t.substring(1)));
            }
        }
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/ijk-small.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        ini_map();
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            in.nextInt();
            int times = Integer.parseInt(in.nextLine().trim());
            String line = in.nextLine();
            boolean res = dijkstra(line, times);
            if (res) {
                out.println("case #" + (i+1) + ": YES");
            } else {
                out.println("case #" + (i+1) + ": NO");
            }
        }
    }

    private static void ini_map() {
        map.put(new Chars("1", "1"), "1");
        map.put(new Chars("1", "i"), "i");
        map.put(new Chars("1", "j"), "j");
        map.put(new Chars("1", "k"), "k");

        map.put(new Chars("i", "1"), "i");
        map.put(new Chars("i", "i"), "-1");
        map.put(new Chars("i", "j"), "k");
        map.put(new Chars("i", "k"), "-j");

        map.put(new Chars("j", "1"), "j");
        map.put(new Chars("j", "i"), "-k");
        map.put(new Chars("j", "j"), "-1");
        map.put(new Chars("j", "k"), "i");

        map.put(new Chars("k", "1"), "k");
        map.put(new Chars("k", "i"), "j");
        map.put(new Chars("k", "j"), "-i");
        map.put(new Chars("k", "k"), "-1");
    }
}
