package milkshakes;

import utility.MyIn;

import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean canSatisfyWithout() {
        return removeCount < orders.length;
    }

    public int getRemoveCount() {
        return removeCount;
    }

    public void updateRemove(boolean removed) {
        if (!removed) {
            removeCount--;
        }
        removeCount++;
    }
}
