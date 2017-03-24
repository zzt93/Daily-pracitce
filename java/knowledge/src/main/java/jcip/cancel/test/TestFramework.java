package jcip.cancel.test;

import jcip.buildingBlock.Cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

/**
 * Created by zzt on 4/29/16.
 * <p>
 * Usage: stop a job when it is time-out
 */
public class TestFramework {

    private static final String TESTER = "Tester";
    private static ScheduledExecutorService service
            = Executors.newScheduledThreadPool(10);

    /**
     * Load class from a directory then test
     */
    public void loadAndTest(String className) {
        // classLoader
        TesterClassLoader loader = new TesterClassLoader();
        Tester tester;
        try {
            Class<?> aClass = loader.loadClass(className, true);
            tester = (Tester) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Can't find this class: " + className);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("This class has no default constructor");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Don't have access right");
        }
        long start = System.nanoTime();
        try {
            timedRun(tester::test, TimeUnit.SECONDS, 1L);
        } catch (InterruptedException e) {
            System.out.println("Time limit exceed");
            return;
        }
        System.out.println("class: " + className + ";" + (System.nanoTime() - start));

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

    private static final class TesterClassLoader extends ClassLoader {
        TesterClassLoader() {
            super(TestFramework.class.getClassLoader());
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            if (!TESTER.equals(name)) {
                return super.loadClass(name, resolve);
            }
            try {
                Path path = Paths.get(name + ".class");
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
