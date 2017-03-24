package thread.executor;

import hw2l.InvokeLive;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 5/28/15.
 * <p>
 * Description:
 */
public class Schedule {
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
    private InvokeLive command;
    private ScheduledFuture<?> future;
    private int period = 0;

    private void live(int period) {
        this.period = period;
        command = new InvokeLive(period);

        future = executorService.scheduleWithFixedDelay(
                command, 1, 10, TimeUnit.SECONDS
        );
//        executorService.scheduleWithFixedDelay(
//                new InvokeLiveCall(period), 1, 10, TimeUnit.SECONDS
//        )
    }

    public void cancel() {
        executorService.shutdown();
    }

    public ArrayList<String> getLive(int period) throws InterruptedException {
        checkPeriod(period);
        if (this.period != period) {
            if (future != null) {
                future.cancel(true);
            }
            live(period);
        }
        return command.getLive();
    }

    private void checkPeriod(int period) {
        if (period > 4 || period < 1) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        try {
            new Schedule().getLive(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
