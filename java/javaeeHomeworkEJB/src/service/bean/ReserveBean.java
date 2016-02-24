package service.bean;

import entity.Reserve;
import service.ReserveService;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "ReserveEJB")
public class ReserveBean implements ReserveService {
    public ReserveBean() {
    }


    @Override
    public boolean reserveAdd(Reserve reserve) {
        return false;
    }

    @Override
    public boolean reserveEdit(Reserve reserve) {
        return false;
    }

    @Override
    public boolean reserveDelete(int rid) {
        return false;
    }

    @Override
    public boolean reserveFinish(int rid) {
        return false;
    }

    @Override
    public ArrayList<Reserve> reserveList() {
        return null;
    }
}
