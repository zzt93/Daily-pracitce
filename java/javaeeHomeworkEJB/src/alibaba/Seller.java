package alibaba;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zzt on 4/20/16.
 * <p>
 * Usage:
 */
public class Seller {
    private List<Strategy> strategies = new CopyOnWriteArrayList<>();
    private Map<Address, BigDecimal> deliveryFees = new ConcurrentHashMap<>();
    private Map<Double, BigDecimal> discounts = new ConcurrentHashMap<>();

    public Strategy bestStrategy(User user, ArrayList<Goods> goodses) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Goods goodse : goodses) {
            sum = sum.add(goodse.getPrice());
        }
        BigDecimal best = BigDecimal.ZERO;
        Strategy res = new EmptyStrategy();
        BigDecimal tmp;
        for (Strategy strategy : strategies) {
            tmp = strategy.discount(sum, user, new BigDecimal("100"));
            if (tmp.compareTo(best) > 0) {
                best = tmp;
                res = strategy;
            }
        }
        return res;
    }

    public interface Strategy {
        BigDecimal discount(BigDecimal sum, User user, BigDecimal threshold);
    }

    private class DeliveryStrategy implements Strategy {

        @Override
        public BigDecimal discount(BigDecimal sum, User user, BigDecimal threshold) {
            BigDecimal delivery = deliveryFees.get(user.getAddress());
            BigDecimal res = BigDecimal.ZERO;
            if (sum.compareTo(threshold) > 0) {
                res = delivery;
            }
            return res;
        }
    }

    private class DiscountStrategy implements Strategy {

        @Override
        public BigDecimal discount(BigDecimal sum, User user, BigDecimal threshold) {
            BigDecimal res = BigDecimal.ZERO;
            if (sum.compareTo(threshold) > 0) {
                res = discounts.get(threshold.doubleValue());
            }
            return res;
        }
    }

    private class EmptyStrategy implements Strategy {
        @Override
        public BigDecimal discount(BigDecimal sum, User user, BigDecimal threshold) {
            return BigDecimal.ZERO;
        }
    }
}
