package call;

import sharedByDifferentStyle.GoodsItem;

import java.util.ArrayList;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class Sale {
    private ArrayList<GoodsItem> goodsArrayList = new ArrayList<>();

    public Sale() {
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
}
