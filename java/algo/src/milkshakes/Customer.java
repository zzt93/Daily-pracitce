package milkshakes;

import utility.MyIn;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.HashSet;
=======
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
import java.util.List;

/**
 * Created by zzt on 3/28/15.
 */
public class Customer{
    private Order[] orders;
    private int removeCount;

    public Customer(MyIn in) {
        int num = in.nextInt();

        orders = new Order[num];

        ArrayList<Integer> tmp = in.oneLineToInt(" ");
        assert (tmp.size() / 2) == num;
        for (int i = 0; i < tmp.size(); i+=2) {
            Integer flavor = tmp.get(i);
            Integer unmalt = tmp.get(i + 1);

            orders[i/2] = new Order(flavor, unmalt, this);
        }
        Arrays.sort(orders);
        removeCount = 0;
    }

    public List<Order> getOrders() {
        return Arrays.asList(orders);
    }

<<<<<<< HEAD
    public boolean canSatisfyForNow() {
        return removeCount < orders.length;
    }

=======
    public boolean canSatisfyWithout() {
        return removeCount < orders.length;
    }

    public int getRemoveCount() {
        return removeCount;
    }
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c

    public void updateRemove(boolean removed) {
        if (!removed) {
            removeCount--;
<<<<<<< HEAD
        } else {
            removeCount++;
        }
    }

    public boolean canSatisfyWithout() {
        return removeCount + 1 < orders.length;
    }

    public void getRemoved(HashSet<Integer> option) {
        for (Order order : orders) {
            if (order.isRemoved()) {
                option.add(order.getFlavor());
            }
        }
=======
        }
        removeCount++;
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
    }
}
