package service;

import entity.Reserve;
import entity.ReserveDetail;
import mis.ChartDataWrapper;
import service.exception.BalanceNotEnoughException;
import tmpEntity.ReserveBranchVO;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Remote
public interface ReserveService {

    Reserve reserveAddAndPay(ReserveBranchVO reserveBranchVO) throws BalanceNotEnoughException;
    boolean reserveEdit(Reserve reserve);
    boolean reserveDelete(int rid);
    boolean reserveFinish(int rid);
    Reserve reserveGet(int rid);

    ArrayList<Reserve> userReserve(int uid, int startIndex, int pageSize);
    long countUserReserve(int uid);
    ArrayList<Reserve> branchReserve(int bid, int startSize, int pageSize);
    long countBranchReserve(int bid);
    ArrayList<Reserve> branchUserReserve(int bid, int uid, int startIndex, int pageSize);
    long countBranchUserReserve(int bid, int uid);
    ArrayList<Reserve> userPayment(int uid, int startIndex, int pageSize);
    long countUserPayment(int uid);

    Reserve branchUserReserveDetail(int bid, int uid, String buyDate);
    List<ReserveDetail> reserveDetailGet(int rid);
    boolean reserveDetailDelete(int rdid);
    ReserveDetail reserveDetailAdd(int rid, int did, int num, double price);
    ReserveDetail reserveDetailUpdateNum(int rdid, int num);

    HashMap<Boolean, long[]> reserveCountByBranch();
    ChartDataWrapper saleSumByDessert();


}
