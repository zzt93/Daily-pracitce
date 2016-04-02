package inheritance;

/**
 * Created by zzt on 4/2/16.
 * <p>
 * Usage:
 */


public class ThisClass {

    public static void main(String[] args) {
        Father father = new Son();
        father.des(father);
    }
}
