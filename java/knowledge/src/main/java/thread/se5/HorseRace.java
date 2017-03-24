package thread.se5;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 4/12/16.
 * <p>
 * Usage:
 */
class Horse implements Runnable {
    private static final int BOUND = 3;
    private static AtomicInteger count = new AtomicInteger(1);
    private CyclicBarrier barrier;
    private int move;
    private String id;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
        id = "马" + count.getAndAdd(1);
    }

    @Override
    public void run() {
        while (true) {
            move();
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                return;
            }
        }
    }

    private void move() {
        int m = ThreadLocalRandom.current().nextInt(BOUND);
        move += m;
    }

    public boolean isWin(int end) {
        return move >= end;
    }

    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }

    private int getStrides() {
        return move;
    }

    public String getId() {
        return id;
    }
}

public class HorseRace {
    private static final int END = 20;

    public HorseRace(int horseNum, int pause) {
        ArrayList<Horse> horses = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(horseNum, () -> {
            printFence();
            for (Horse horse : horses) {
                System.out.println(horse.tracks());
            }
            printFence();
            System.out.println();
            for (Horse horse : horses) {
                if (horse.isWin(END)) {
                    System.out.println(horse.getId() + " won!");
                    service.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < horseNum; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            service.execute(horse);
        }
    }

    private void printFence() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < END; i++) {
            s.append("="); // The fence on the racetrack
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        if (args.length > 0) { // Optional argument
            int n = new Integer(args[0]);
            nHorses = n > 0 ? n : nHorses;
        }
        if (args.length > 1) { // Optional argument
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }
        new HorseRace(nHorses, pause);
    }
}
