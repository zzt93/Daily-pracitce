package mvc;

import call.SalePrompt;
import object.SaleDelegate;
import sharedByDifferentStyle.User;

import java.util.Scanner;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class Controller {

    private final SaleView view;
    private UserModel userModel = new UserModel();
    private SaleModel saleModel = new SaleModel();
    private SaleDelegate saleDelegate;

    public Controller(SaleView saleView) {
        view = saleView;
    }

    public void sale() {
        System.out.println("sale started");

        System.out.println("input user account and password for this sale:");
        Scanner in = view.getIn();
        User user = getUser(in.next(), in.next());
        saleDelegate = saleModel.getSale(user);
        in.nextLine();

        SalePrompt.addGoodsPrompt(saleDelegate.getSale(), in);
        System.out.println("sum: " + saleDelegate.getSum());
        System.out.println("after strategy: " + saleDelegate.getActual());
    }

    private User getUser(String name, String pw) {
        return userModel.getUser(name, pw);
    }

    public void charge() {
        System.out.println("now pay how much?");
        Scanner in = view.getIn();
        int pay = in.nextInt();
        while (!saleDelegate.validPay(pay)) {
            System.out.println("you should pay more");
            System.out.println("now pay how much?");
            pay = in.nextInt();
        }
        System.out.println("now give you change: " + saleDelegate.change(pay));
    }

    public void bill() {
        saleDelegate.print();
    }
}
