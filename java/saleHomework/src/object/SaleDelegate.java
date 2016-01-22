package object;

import call.Sale;
import sharedByDifferentStyle.GoodsItem;
import sharedByDifferentStyle.SaleBasic;
import sharedByDifferentStyle.User;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage: maintain the goods list for a sale, so calculate the total price and give the change depending on the payment
 */
public class SaleDelegate {

    private GoodsPrinter printer;
    call.Sale sale = new call.Sale();

    public SaleDelegate(GoodsPrinter printer) {
        this.printer = printer;
    }

    public SaleDelegate(User user) {
        setUser(user);
        printer = new SimpleGoodsPrinter();
    }

    public boolean validPay(double pay) {
        return sale.validPay(pay);
    }

    public double change(double pay) {
        return sale.change(pay);
    }

    public void print() {
        printer.head();
        System.out.println(sale.getUser().inBill());
        sale.getGoodsArrayList().forEach(printer::item);
        System.out.println("sum: " + sale.getSum());
        System.out.println("after strategy: " + getActual());
        printer.end();
    }

    public void setUser(User user) {
        sale.setUser(user);
    }


    public double getActual() {
        return sale.getActual();
    }

    public boolean addGoods(GoodsItem goodsItem) {
        return sale.addGoods(goodsItem);
    }

    public double getSum() {
        return sale.getSum();
    }

    public Sale getSale() {
        return sale;
    }
}
