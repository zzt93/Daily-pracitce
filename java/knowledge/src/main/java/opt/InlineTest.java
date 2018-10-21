package opt;

import java.util.Random;

/**
 * Created by zzt on 1/4/17.
 * <p>
 * -XX:+CITime -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining -XX:+PrintCompilation -XX:CompileCommand=dontinline,opt.InlineTest::fib
 * <p>-XX:+CITime -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining -XX:+PrintCompilation  </p>
 */
public class InlineTest {

    private static int fib(int i) {
        if (i == 0 || i == 1) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }

    public static void main(String[] args) {
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            //            fib(random.nextInt(5) + 25);
            fib(28);
        }
        long endTime = System.nanoTime();
        System.out.format("%.2f seconds elapsed.\n", (endTime - startTime) / 1000.0 / 1000 / 1000);
    }
}
