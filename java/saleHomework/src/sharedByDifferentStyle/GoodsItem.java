package sharedByDifferentStyle;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class GoodsItem {
    private Goods goods;
    private int num;

    public GoodsItem(Goods goods, int num) {
        this.goods = goods;
        this.num = num;
    }

    public double getPrice() {
        return goods.getPrice() * num;
    }
}
