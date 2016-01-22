package object;

import call.SalePrompt;
import sharedByDifferentStyle.User;

import java.util.Scanner;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class SaleUI {

    private Scanner scanner;
    private final SaleDelegate saleDelegate;

    public SaleUI(Scanner scanner) {
        this.scanner = scanner;
        saleDelegate = new SaleDelegate(new SimpleGoodsPrinter());
    }

    public void sale() {
        System.out.println("sale started");

        System.out.println("input user account and password for this sale:");
        User user = new User(scanner.next(), scanner.next());
        saleDelegate.setUser(user);
        scanner.nextLine();

        SalePrompt.addGoodsPrompt(saleDelegate.getSale(), scanner);
        System.out.println("sum: " + saleDelegate.getSum());
        System.out.println("after strategy: " + saleDelegate.getActual());
    }

    public void charge() {
        System.out.println("now pay how much?");
        int pay = scanner.nextInt();
        while (!saleDelegate.validPay(pay)) {
            System.out.println("you should pay more");
            System.out.println("now pay how much?");
            pay = scanner.nextInt();
        }
        System.out.println("now give you change: " + saleDelegate.change(pay));
    }

    public void bill() {
        saleDelegate.print();
    }
}

