package sharedByDifferentStyle;

import java.util.ArrayList;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public abstract class SaleBasic {
    private ArrayList<GoodsItem> goodsArrayList = new ArrayList<>();
    private double sum = 0;


    public boolean addGoods(GoodsItem goodsItem) {
        sum += goodsItem.getPrice();
        return goodsArrayList.add(goodsItem);
    }

    public double getSum() {
        return sum;
    }

    public ArrayList<GoodsItem> getGoodsArrayList() {
        return goodsArrayList;
    }
}
