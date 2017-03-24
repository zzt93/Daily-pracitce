package java;

/**
 * Created by zzt on 10/5/16.
 * <p>
 * <h3></h3>
 */
public class CompilingChar {

    private static final int A = 4000;

    public static void main(String[] args) {
        final char c2 = '鱀';
        System.out.println(c2);
        final char x = '\u4000';
        System.out.println(x);
        System.out.println('c');
        System.out.println(A);
    }
}
