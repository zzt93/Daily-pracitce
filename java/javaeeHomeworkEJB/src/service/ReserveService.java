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

    boolean reserveAdd(Reserve reserve);
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

}
