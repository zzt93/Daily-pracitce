package so_test;

/**
 * Created by zzt on 1/1/17.
 * <p>
 * <h3></h3>
 */
public class StrangeBehaviour {

    static boolean recursionFlag = true;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            functionA(6, 0);
        }
        long endTime = System.nanoTime();
        System.out.format("%.2f seconds elapsed.\n", (endTime - startTime) / 1000.0 / 1000 / 1000);
    }

    static boolean functionA(int recursionDepth, int recursionSwitch) {
        if (recursionDepth == 0) {
            return true;
        }
        return functionB(recursionDepth, recursionSwitch);
    }

    static boolean functionB(int recursionDepth, int recursionSwitch) {
        for (int i = 0; i < 16; i++) {
            if (StrangeBehaviour.recursionFlag) {
                //                int a = 1;
                //                int b = 1;
                if (recursionSwitch == 0) {
                    if (functionA(recursionDepth - 1, 1 - recursionSwitch)) return true;
                } else {
                    if (!functionA(recursionDepth - 1, 1 - recursionSwitch)) return false;
                }
            } else {
                // This block is never entered into.
                // Yet commenting out one of the lines below makes the program run slower!
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                //                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
                System.out.println("...");
            }
        }
        return false;
    }
}