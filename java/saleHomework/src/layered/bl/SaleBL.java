package layered.bl;

import object.SaleDelegate;
import sharedByDifferentStyle.User;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class SaleBL {

    private static SaleBL sale;

    public static SaleBL getInstance() {
        if (sale == null) {
            sale = new SaleBL();
        }
        return sale;
    }

    private SaleBL() {
    }

    public SaleDelegate getSale(User user) {
        return new SaleDelegate(user);
    }
}
