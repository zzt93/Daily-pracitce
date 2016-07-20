package inter;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public interface Vararg {

    void hello(String... strings);
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