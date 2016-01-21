package object;

import sharedByDifferentStyle.GoodsItem;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public interface GoodsPrinter {
    void head();
    void end();
    void item(GoodsItem goods);
}
