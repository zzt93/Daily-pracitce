package object;

import sharedByDifferentStyle.Goods;
import sharedByDifferentStyle.GoodsItem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 * maintain the goods list for a sale, so calculate the total price and give the
 * change depending on the payment
 */
public class Sale {

    private ArrayList<GoodsItem> goodsArrayList = new ArrayList<>();
    private GoodsPrinter printer;

    public Sale(GoodsPrinter printer) {
        this.printer = printer;
    }

    public boolean addGoods(GoodsItem goods) {
        return goodsArrayList.add(goods);
    }

    public ArrayList<GoodsItem> getGoodsArrayList() {
        return goodsArrayList;
    }

    public double change(double pay) {
        double sum = goodsArrayList.stream().mapToDouble(GoodsItem::getPrice).sum();
        return pay - sum;
    }

    public void print() {
        printer.head();
        goodsArrayList.forEach(printer::item);
        printer.end();
    }

}
