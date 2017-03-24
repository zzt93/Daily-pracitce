package memory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zzt on 9/8/16.
 * <p>
 * changed from org.jboss.netty.util.DefaultObjectSizeEstimator
 * <h3></h3>
 */
public class DefaultObjectSizeEstimator {

    private final ConcurrentMap<Class<?>, Integer> class2size =
            new ConcurrentHashMap<>();

    /**
     * Creates a new instance.
     */
    public DefaultObjectSizeEstimator() {
        class2size.put(boolean.class, 4); // Probably an integer.
        class2size.put(byte.class, 1);
        class2size.put(char.class, 2);
        class2size.put(int.class, 4);
        class2size.put(short.class, 2);
        class2size.put(long.class, 8);
        class2size.put(float.class, 4);
        class2size.put(double.class, 8);
        class2size.put(void.class, 0);
    }

    public int estimateSize(Object o) {
        if (o == null) {
            return 8;
        }

        int answer = 8 + estimateSize(o.getClass(), null);

        if (o instanceof byte[]) {
            answer += ((byte[]) o).length;
        } else if (o instanceof ByteBuffer) {
            answer += ((ByteBuffer) o).remaining();
        } else if (o instanceof CharSequence) {
            answer += ((CharSequence) o).length() << 1;
        } else if (o instanceof Iterable<?>) {
            for (Object m : (Iterable<?>) o) {
                answer += estimateSize(m);
            }
        }

        return align(answer);
    }

    private int estimateSize(Class<?> clazz, Set<Class<?>> visitedClasses) {
        Integer objectSize = class2size.get(clazz);
        if (objectSize != null) {
            return objectSize;
        }

        if (visitedClasses != null) {
            if (visitedClasses.contains(clazz)) {
                return 0;
            }
        } else {
            visitedClasses = new HashSet<Class<?>>();
        }

        visitedClasses.add(clazz);

        int answer = 8; // Basic overhead.
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                if ((f.getModifiers() & Modifier.STATIC) != 0) {
                    // Ignore static fields.
                    continue;
                }

                answer += estimateSize(f.getType(), visitedClasses);
            }
        }

        visitedClasses.remove(clazz);

        // Some alignment.
        answer = align(answer);

        // Put the final answer.
        class2size.putIfAbsent(clazz, answer);
        return answer;
    }

    private static int align(int size) {
        int r = size % 8;
        if (r != 0) {
            size += 8 - r;
        }
        return size;
    }

    private static class IntArray {
        private int[] a = new int[16];
    }

    private static class ByteArray {
        private byte[] a = new byte[16];
    }

    private static class Ints {
        private int a;
        private int b;
    }

    private static class IntArrays {
        private IntArray a = new IntArray();
        private IntArray b = new IntArray();
    }

    public static void main(String[] args) {

        final DefaultObjectSizeEstimator estimator = new DefaultObjectSizeEstimator();
        System.out.println(estimator.estimateSize(new IntArrays()));
        System.out.println(estimator.estimateSize(new IntArray()));
        System.out.println(estimator.estimateSize(new ByteArray()));
        System.out.println(estimator.estimateSize(new Ints()));
        System.out.println(estimator.estimateSize(new byte[16]));
    }
}
