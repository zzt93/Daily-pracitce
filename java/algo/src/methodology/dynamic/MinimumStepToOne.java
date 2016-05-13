package methodology.dynamic;

import competition.utility.ArrayUtility;

import java.util.Arrays;

/**
 * Created by zzt on 5/11/16.
 * <p>
 * <h3>Problem Statement: </h3>
 * <p>
 * On a positive integer, you can perform any one of the following 3 steps.
 * <li>Subtract 1 from it. ( n = n - 1 )</li>
 * <li>If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2)</li>
 * <li>If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3)</li>.
 * Now the question is, given a positive integer n,
 * find the minimum number of steps that takes n to 1
 * </p>
 * <pre>
 * eg:
 * 1.)For n = 1 , output: 0
 * 2.)For n = 4 , output: 2  ( 4  /2 = 2  /2 = 1 )
 * 3.)For n = 7 , output: 3  (  7  -1 = 6   /3 = 2   /2 = 1 )
 * </pre>
 */
public class MinimumStepToOne {

    private static final int SIZE = 10001;
    /**
     * if {@link #SIZE} < the range of number, it get very slow
     */
    private static int[] table = new int[SIZE];

    public static int minimumStepMem(int origin) {
        if (origin == 1) {
            return 0;
        }
        if (origin < SIZE && table[origin] != 0) {
            return table[origin];
        }
        int[] three = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        if (origin % 3 == 0) {
            three[2] = minimumStepMem(origin / 3);
        }
        if (origin % 2 == 0) {
            three[1] = minimumStepMem(origin / 2);
        }
        three[0] = minimumStepMem(origin - 1);
        Arrays.sort(three);
        if (origin < SIZE) {
            table[origin] = three[0] + 1;
        }
        return three[0] + 1;
    }

    private static int[] dp = new int[SIZE];
    private static int max = 1;
    public static int minimumStepDP(int origin) {
        if (origin == 1) {
            return 1;
        }
        if (origin < SIZE && dp[origin] != 0) {
            return dp[origin];
        }
        dp[1] = 0;
        for (int i = max + 1; i <= origin; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        max = Math.max(max, origin);
        return dp[origin];
    }

    public static void main(String[] args) {
        final Integer[] integers = ArrayUtility.randomIntegers(23, 10, 1, 10000);
        for (Integer i : integers) {
            System.out.println(i + ":" + minimumStepMem(i));
        }
        System.out.println(minimumStepMem(7));
        for (Integer i : integers) {
            System.out.println(i + ":" + minimumStepDP(i));
        }
        System.out.println(minimumStepMem(7));
    }
}
