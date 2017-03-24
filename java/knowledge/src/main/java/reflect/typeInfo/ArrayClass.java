package reflect.typeInfo;

/**
 * Created by zzt on 4/24/16.
 * <p>
 * Usage:
 * The class loader for an array class, as returned by Class.getClassLoader()
 * is the same as the class loader for its element type;
 * if the element type is a primitive type, then the array class has no class loader.
 */
public class ArrayClass {
    public static void main(String[] args) {
        int[] ints = new int[3];
        printInts(ints);
    }

    private static void printInts(int[] ints) {
        Class<? extends int[]> aClass = ints.getClass();
        System.out.println("class: " + aClass.getCanonicalName());
        System.out.println("class loader: " + aClass.getClassLoader());
        System.out.println(aClass.getSuperclass());
        for (Class<?> c : aClass.getInterfaces()) {
            System.out.print(c + "; ");
        }
    }
}
