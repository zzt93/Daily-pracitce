package jcip.cancel;

import jcip.buildingBlock.Cache;
import so_test.ClassLoaderLeakExample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

/**
 * Created by zzt on 4/29/16.
 * <p>
 * Usage:
 */
public class TestFramework {

    public static final String SOLUTION = "Solution";
    private static ScheduledExecutorService service
             = Executors.newScheduledThreadPool(10);

    /**
     * Load class from a directory then test
     */
    public void loadAndTest() {
        // classLoader
        SolutionClassLoader loader = new SolutionClassLoader();
        try {
            Class<?> aClass = loader.loadClass(SOLUTION, true);
            Object solution = aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // reflect
    }

    public static void timedRun(Runnable r, TimeUnit unit,
                                Long time) throws InterruptedException {
        Future<?> f = service.submit(r);
        try {
            f.get(time, unit);
        } catch (ExecutionException e) {
            throw Cache.launderThrowable(e);
        } catch (TimeoutException ignored) {
        } finally {
            f.cancel(true);
        }
    }

    private static final class SolutionClassLoader extends ClassLoader {
        SolutionClassLoader() {
            super(TestFramework.class.getClassLoader());
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            if (!SOLUTION.equals(name)) {
                return super.loadClass(name, resolve);
            }
            try {
                Path cwd = Paths.get(".").toAbsolutePath().normalize();

                Path path = Paths.get("Solution.class");
                byte[] classBytes = Files.readAllBytes(path);
                Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            } catch (IOException ex) {
                throw new ClassNotFoundException("Could not load " + name, ex);
            }
        }
    }
}
