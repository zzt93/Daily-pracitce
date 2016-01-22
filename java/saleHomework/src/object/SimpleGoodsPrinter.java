package object;

import sharedByDifferentStyle.GoodsItem;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class SimpleGoodsPrinter implements GoodsPrinter{
    @Override
    public void head() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("A simple bill format");
    }

    @Override
    public void end() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void item(GoodsItem goods) {
        System.out.println(goods);
    }
}
