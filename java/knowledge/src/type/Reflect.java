package type;

import java.io.Serializable;

/**
 * Created by zzt on 9/11/15.
 * <p>
 * Description: learning reflection
 */
public class Reflect implements Serializable {
    public void testSuper() {
        Class<?> reflectClass = null;
        try {
            reflectClass = Class.forName("type.Reflect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(reflectClass.getSuperclass().getCanonicalName());
        for (Class<?> inter : reflectClass.getInterfaces()) {
            System.out.println(inter.getName());
        }
    }

    public static void main(String[] args) {
        final Reflect reflect = new Reflect();
        reflect.testSuper();
    }
}
