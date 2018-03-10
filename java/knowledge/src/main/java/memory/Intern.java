package memory;

/**
 * Created by zzt on 5/19/16.
 * <p>
 * <h3></h3>
 */
public class Intern {

    public static void main(String[] args) {
        InternFirst();
        InternLater();
    }

    private static void InternLater() {
        String s1 = new String("1");
        String s2 = "1";
        s1.intern();
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s3 == s4);
        System.out.println(s5 == s4);
    }

    private static void InternFirst() {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        String s5 = s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
    }
}
