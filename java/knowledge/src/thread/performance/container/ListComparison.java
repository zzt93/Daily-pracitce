package thread.performance.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/15/16.
 * <p>
 * Usage:
 */
abstract class ListTest extends Tester<List<Integer>> {

    ListTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    private class Reader extends TestTask {

        private long result;

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int j = 0; j < containerSize; j++) {
                    result += testContainer.get(j);
                }
            }
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    private class Writer extends TestTask {

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    testContainer.set(index, writeData[index]);
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

class SynchronizedArrayListTest extends ListTest {

    SynchronizedArrayListTest(int nReaders, int nWriters) {
        super("SynchronizedArrayList", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return Collections.synchronizedList(
                new ArrayList<>(
                        new Random().ints(containerSize, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).boxed().collect(Collectors.toList())
                )
        );
    }
}

class CopyOnWriteArrayListTest extends ListTest {

    CopyOnWriteArrayListTest(int nReaders, int nWriters) {
        super("CopyOnWriteArrayList", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return new CopyOnWriteArrayList<>(
                new Random().ints(containerSize).boxed().collect(Collectors.toList())
        );
    }
}

public class ListComparison {

    private static final int SUM = 10;

    public static void main(String[] args) {
        Tester.initMain(args);
        for (int i = 1; i < SUM; i++) {
            new SynchronizedArrayListTest(SUM - i, i);
        }
        for (int i = 1; i < SUM; i++) {
            new CopyOnWriteArrayListTest(SUM - i, i);
        }
    }
}
