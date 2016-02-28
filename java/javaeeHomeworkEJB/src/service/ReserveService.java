package service;

import entity.Reserve;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Remote
public interface ReserveService {

    boolean reserveAdd(String bdate, int uid, int bid);
    boolean reserveEdit(Reserve reserve);
    boolean reserveDelete(int rid);
    boolean reserveFinish(int rid);
    Reserve reserveGet(int rid);

    ArrayList<Reserve> userReserve(int uid, int startIndex, int pageSize);
    int countUserReserve(int uid);
    ArrayList<Reserve> branchReserve(int bid, int startSize, int pageSize);
    int countBranchReserve(int bid);
    ArrayList<Reserve> branchUserReserve(int bid, int uid, int startIndex, int pageSize);
    int countBranchUserReserve(int bid, int uid);
    ArrayList<Reserve> userPayment(int uid, int startIndex, int pageSize);
    int countUserPayment(int uid);

    Reserve branchUserReserveDetail(int bid, int uid, String buyDate);
    boolean reserveDetailDelete(int rdid);
    boolean reserveDetailAdd(int rid, int did, int num, double price);
    boolean reserveDetailUpdateNum(int rdid, int num);

}
