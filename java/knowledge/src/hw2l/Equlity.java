package hw2l;

import java.util.HashMap;

/**
 * Created by zzt on 5/2/15.
 * <p>
 * Description:
 */
public class Equlity {
    private static Equlity equal = new Equlity();

    private HashMap<String, Integer> map = new HashMap<>();

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public static Equlity getInstance() {
        return equal;
    }
    public static void main(String[] args) {
        Equlity equlity = Equlity.getInstance();
        Equlity equlity1 = Equlity.getInstance();
        System.out.println(equlity.getMap().hashCode());
        System.out.println(equlity1.getMap().hashCode());
    }
}
