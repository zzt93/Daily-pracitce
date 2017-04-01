package reference;

import java.util.WeakHashMap;

/**
 * Created by zzt on 3/22/16.
 * <p>
 * Usage:
 * <p>
 * WeakReference/SoftReference -- map(key is weak), cache
 */
class Element {
    private String ident;

    public Element(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    public int hashCode() {
        return ident.hashCode();
    }

    public boolean equals(Object r) {
        return r instanceof Element &&
                ident.equals(((Element) r).ident);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing " +
                getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}

public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 100;
        // Or, choose size via the command line:
        if (args.length > 0) {
            size = new Integer(args[0]);
        }
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map =
                new WeakHashMap<>();
        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0) {
                keys[i] = k; // Save as "real" references
            }
            map.put(k, v);
        }
        Value value = map.get(new Key("1"));
        System.gc();
    }
}
