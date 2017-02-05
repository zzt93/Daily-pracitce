package str;

/**
 * Created by zzt on 2/5/17.
 * <p>
 * <h3></h3>
 */
public class Intern {

    public static void main(String[] args) {
        //        String s1 = new StringBuilder("aaa").append("a").toString();
        //        test(s1);
        //        String s4 = new String("1") + new String("1");
        //        test(s4);
        //        test(new String("5"));
        //        test("" + new String("6"));
        //        String special = new String(new char[]{'a', 'z'});
        //        test(special);
        allTwoChar();
    }

    private static void allTwoChar() {
        for (char i = 'a'; i <= 'z'; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                test(new String(new char[]{i, j}));
            }
        }
    }

    public static void test(String s) {
        System.out.println(s + ":" + (s.intern() == s));
    }


}
