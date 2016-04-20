package alibaba;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;

/**
 * Created by zzt on 4/20/16.
 * <p>
 * Usage:
 */
public class User {
    private Address address;
    private List<Goods> cart = new CopyOnWriteArrayList<>();

    Address getAddress() {
        return address;
    }

    public void payCart() {
        HashMap<Seller, ArrayList<Goods>> map = new HashMap<>();
        for (Goods goods : cart) {
            map.putIfAbsent(goods.getSeller(), new ArrayList<>()).add(goods);
        }
        for (Seller seller : map.keySet()) {
            Seller.Strategy strategy = seller.bestStrategy(this, map.get(seller));
            // show strategy
        }
    }

    public static void transfer(Account in, Account out, BigDecimal m) {
        in.transfer(out, m);
    }

    private static class Account {
        private BigDecimal money = BigDecimal.ZERO;
        private Lock lock;

        public Lock getLock() {
            return lock;
        }
        public void transfer(Account out, BigDecimal m) {
            boolean lockRes1 = lock.tryLock();
            boolean lockRes2 = out.getLock().tryLock();
            if (lockRes1
                    && lockRes2) {
                try {
                    BigDecimal add = money.add(m);
                    BigDecimal subtract = out.money.subtract(m);
                    if (subtract.compareTo(BigDecimal.ZERO) < 0) {
                        throw new OutOfMoneyException();
                    }
                    money = add;
                    out.money = subtract;
                } catch (OutOfMoneyException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    out.getLock().unlock();
                }
            } else {
                if (lockRes1) {
                    lock.unlock();
                } else {
                    out.getLock().unlock();
                }
            }
        }

        private class OutOfMoneyException extends Throwable {
        }
    }
}
