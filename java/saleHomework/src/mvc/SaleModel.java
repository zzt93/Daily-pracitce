package mvc;

import layered.bl.SaleBL;
import object.SaleDelegate;
import sharedByDifferentStyle.User;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class SaleModel {

    private SaleBL saleBL = SaleBL.getInstance();


    public SaleDelegate getSale(User user) {
        return saleBL.getSale(user);
    }
}
