package scheduler;

import entity.User;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 3/5/16.
 * <p>
 * Usage:
 */
public class Starter {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startChecking(User user) {
        BalanceChecker.addUser(user);
        scheduler.scheduleAtFixedRate(new BalanceChecker(), 1, 1, TimeUnit.DAYS);
    }
}
