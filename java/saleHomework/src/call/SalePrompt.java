package call;

import sharedByDifferentStyle.Goods;
import sharedByDifferentStyle.GoodsItem;
import sharedByDifferentStyle.SaleBasic;
import sharedByDifferentStyle.User;

import java.util.Scanner;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class SalePrompt {

    private Scanner scanner;

    public SalePrompt(Scanner scanner) {
        this.scanner = scanner;
    }

    public Sale sale() {
        Sale sale = new Sale();
        System.out.println("sale started");

        System.out.println("input user account and password for this sale:");
        User user = new User(scanner.next(), scanner.next());
        sale.setUser(user);
        scanner.nextLine();

        addGoodsPrompt(sale, scanner);
        System.out.println("sum: " + sale.getSum());
        System.out.println("after strategy: " + sale.getActual());
        return sale;
    }

    public static void addGoodsPrompt(SaleBasic sale, Scanner scanner) {
        String goodsName;
        while (true) {
            System.out.println("input the name of goods");
            goodsName = scanner.nextLine();
            if (goodsName.isEmpty()) {
                break;
            }
            System.out.println("input the number of goods(left empty means one)");
            String number = scanner.nextLine();
            number = number.isEmpty() ? "1" : number;
            int num = Integer.parseInt(number);
            sale.addGoods(new GoodsItem(Goods.valueOf(goodsName.toUpperCase().trim()), num));
        }
    }

    public void charge(Sale sale) {
        System.out.println("now pay how much?");
        int pay = scanner.nextInt();
        while (!sale.validPay(pay)) {
            System.out.println("you should pay more");
            System.out.println("now pay how much?");
            pay = scanner.nextInt();
        }
        System.out.println("now give you change: " + sale.change(pay));
    }
}
