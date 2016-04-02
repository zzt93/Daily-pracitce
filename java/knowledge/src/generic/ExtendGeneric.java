package generic;

/**
 * Created by zzt on 4/2/16.
 * <p>
 * Usage:
 */
class Generic<T> {
    private T t;

    public Generic(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

class Int extends Generic<Integer> {

    public Int(Integer integer) {
        super(integer);
    }
}

class Str extends Generic<String> {
    public Str(String s) {
        super(s);
    }
}

public class ExtendGeneric {
    public static void main(String[] args) {
        Int anInt = new Int(1);
        Integer t = anInt.getT();
        System.out.println(t);
        Str str = new Str("ha");
        String string = str.getT();
        System.out.println(string);
    }
}
