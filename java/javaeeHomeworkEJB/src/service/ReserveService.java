package service;

import entity.Reserve;

import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
public interface ReserveService {

    boolean reserveAdd(int uid, int bid, String bdate);
    boolean reserveEdit(Reserve reserve);
    boolean reserveDelete(int rid);
    boolean reserveFinish(int rid);
    ArrayList<Reserve> reserveList();
}
