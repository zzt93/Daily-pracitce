package inter;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public interface Vararg {

    void hello(String... strings);


    public static void main(String[] args) {
        String[] strings = {"a", "b"};
        Object[] objects = {new Object(), new Object()};
        new VarargImpl2().hello(strings);
        System.out.println(objects.getClass());
        System.out.println(strings.getClass());
    }
}

class VarargImpl implements Vararg {

    @Override
    public void hello(String[] strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
}

class VarargImpl2 implements Vararg {

    @Override
    public void hello(String... strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
}