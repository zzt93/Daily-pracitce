package layered.view;

import call.Sale;
import sharedByDifferentStyle.Goods;
import sharedByDifferentStyle.GoodsItem;
import sharedByDifferentStyle.User;

import java.util.Scanner;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class SaleUI {

    private Scanner scanner;

    public SaleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public Sale sale() {
        Sale sale = new Sale();
        System.out.println("sale started");

        System.out.println("input user account and password for this sale:");
        User user = new User(scanner.next(), scanner.next());

        while (true) {
            System.out.println("input the name of goods");
            String goodsName = scanner.next();
            if (goodsName.isEmpty()) {
                break;
            }
            System.out.println("input the number of goods(left empty means one)");
            String number = scanner.next();
            number = number.isEmpty() ? "1" : number;
            int num = Integer.parseInt(number);
            sale.addGoods(new GoodsItem(Goods.valueOf(goodsName), num));
        }
        return sale;
    }
}
