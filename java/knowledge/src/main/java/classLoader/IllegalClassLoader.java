package classLoader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zzt on 4/24/16.
 * <p>
 * Usage:
 */
public class IllegalClassLoader extends ClassLoader {
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Path path = Paths.get(name + ".class");
        byte[] classBytes;
        try {
            classBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ClassNotFoundException("Could not load " + name, e);
        }
        Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        IllegalClassLoader illegalClassLoader = new IllegalClassLoader();
        Class<?> aClass = Class.forName(Integer.class.getName(), true, illegalClassLoader);
        getSystemResource(aClass.getCanonicalName());
    }
}
