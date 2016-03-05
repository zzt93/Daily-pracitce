package scheduler;

import entity.Consume;
import entity.User;
import mis.CardState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zzt on 3/5/16.
 * <p>
 * Usage:
 */
public class BalanceChecker implements Runnable {

    private static final ArrayList<User> users = new ArrayList<>();

    @Override
    public void run() {
        synchronized (users) {
            for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
                User user = iterator.next();
                Consume consume = user.getConsume();
                if (consume.getBalance() > 0) {
                    iterator.remove();
                    // update card state
                    consume.setState((byte) CardState.movePrevious(consume.getState()).ordinal());
                } else { // owe me money
                    String s = consume.getnDate();
                    // overdue -- change state
                    if (s.compareTo(LocalDate.now().toString()) <= 0) {
                        byte cardState = consume.getState();
                        CardState next = CardState.moveNext(cardState);
                        consume.setState((byte) next.ordinal());
                    }
                }
            }
        }
    }

    public static void addUser(User user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public static boolean noUser() {
        synchronized (users) {
            return users.isEmpty();
        }
    }

}
