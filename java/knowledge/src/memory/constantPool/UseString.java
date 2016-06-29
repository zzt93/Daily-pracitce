package memory.constantPool;

import java.util.Scanner;

/**
 * Created by zzt on 6/29/16.
 * <p>
 * <h3></h3>
 */
public class UseString {

    public static final String str = "string";
    public static final String str2 = "string";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("str == UseSameStr.str2 is same address: " + str == UseSameStr.str2);
        System.out.println("str == str2 is same address: " + str == str2);
    }
}
