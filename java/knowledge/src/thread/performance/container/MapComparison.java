package thread.performance.container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/15/16.
 * <p>
 * Usage:
 */
abstract class MapTester extends Tester<Map<Integer, Integer>> {

    MapTester(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        private long result;

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (Integer integer : testContainer.keySet()) {
                    result += testContainer.get(integer);
                }
            }
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                int index = 0;
                for (Integer integer : testContainer.keySet()) {
                    testContainer.put(integer, writeData[index++]);
                }
            }
        }

        @Override
        void putResults() {
            writeTime += duration;
        }
    }

    @Override
    void startReaderAndWrite() {
        for (int i = 0; i < nReaders; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < nWriters; i++) {
            exec.execute(new Writer());
        }
    }
}

class SynchronizedHashMap extends MapTester {

    SynchronizedHashMap(int nReaders, int nWriters) {
        super("SynchronizedHashMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> containerInitializer() {
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        Random random = new Random();
        for (int i = 0; i < containerSize; i++) {
            map.put(i, random.nextInt());
        }
        return map;
    }
}

class ConcurrentHashMapTest extends MapTester {

    ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMapTest", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> containerInitializer() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        Random random = new Random();
        for (int i = 0; i < containerSize; i++) {
            map.put(i, random.nextInt());
        }
        return map;
    }
}

public class MapComparison {
    private static final int SUM = 10;

    public static void main(String[] args) {
        Tester.initMain(args);
        for (int i = 1; i < SUM; i++) {
            new SynchronizedHashMap(SUM - i, i);
        }
        for (int i = 1; i < SUM; i++) {
            new ConcurrentHashMapTest(SUM - i, i);
        }
    }
}
