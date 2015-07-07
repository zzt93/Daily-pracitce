package so_test;

/**
 * Created by zzt on 5/10/15.
 * <p>
 * Description:
 */
public class Nan {
    static double zero = 0;
    static double one = 1;

    public static void main(String[] args) {
        double zeroDzero = zero / zero;
        double oneDzero = one / zero;
        System.out.println(zeroDzero);
        System.out.println(oneDzero);
        System.out.println("NaN == NaN: " + (zeroDzero == zeroDzero));
        System.out.println("NaN < NaN: " + (zeroDzero < zeroDzero));
        System.out.println("NaN > NaN: " + (zeroDzero > zeroDzero));
        System.out.println("number < NaN: " + (one < zeroDzero));
        System.out.println("number > NaN: " + (one > zeroDzero));
        System.out.println("Infinity == Infinity: " + (oneDzero == oneDzero));

//        Exception in thread "main" java.lang.ArithmeticException: / by zero
//        System.out.println(0 / 0);
    }
}
