package call;

import sharedByDifferentStyle.GoodsItem;
import sharedByDifferentStyle.SaleBasic;
import sharedByDifferentStyle.User;

import java.util.ArrayList;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class Sale extends SaleBasic {
    private double actual = 0;
    private User user;

    public Sale() {
    }

    public double change(double pay) {
        return pay - actual;
    }


    public double getActual() {
        actual = user.afterStrategy(getSum());
        return actual;
    }

    public boolean validPay(double pay) {
        return pay >= actual;
    }

    public void printBill() {
        System.out.println("---------------------");
        System.out.println("bill for: " + user.getName());
        getGoodsArrayList().forEach(System.out::print);
        System.out.println("sum: " + getSum());
        System.out.println("after strategy: " + actual);
        System.out.println("---------------------");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
