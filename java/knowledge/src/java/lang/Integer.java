package java.lang;

/**
 * Created by zzt on 4/25/16.
 * <p>
 * Usage:
 * Test about shadowing class:
 * You can create a new class in java.lang package.
 * But you will not be able to load it, because java.lang.ClassLoader
 * (that any classloader extends) does not allow it,
 * every class being loaded goes through this check
 * <pre>
 * if ((name != null) && name.startsWith("java.")) {
 *      throw new SecurityException
 *      ("Prohibited package name: " + name.substring(0, name.lastIndexOf('.')));
 * }
 * </pre>
 */
public class Integer {

    public static void main(String[] args) {
        Integer integer = new Integer();
    }
}
