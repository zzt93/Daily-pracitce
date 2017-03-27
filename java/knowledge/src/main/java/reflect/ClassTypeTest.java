package main.java.reflect;

import java.lang.reflect.TypeVariable;

/**
 * Created by zzt on 3/25/17.
 * <p>
 * <h3></h3>
 */
public class ClassTypeTest {

    private static class Inner<T extends ClassTypeTest, S extends Integer> {
        T t;
        S s;
    }

    public static void main(String[] args) {
        Inner<ClassTypeTest, Integer> inner = new Inner<>();
        Class<? extends Inner> aClass = inner.getClass();
        for (TypeVariable<? extends Class<? extends Inner>> classTypeVariable : aClass.getTypeParameters()) {
            System.out.println(classTypeVariable);
        }
    }
}
