package tmpEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class ReserveBranchVO implements Serializable {
    public static final long serialVersionUID = 42L;


    private final String bdate;
    private final int uid;
    private final int bid;
    private HashMap<Integer, RDBranchVO> details = new HashMap<>();

    public ReserveBranchVO(String bdate, int uid, int bid) {
        this.bdate = bdate;
        this.uid = uid;
        this.bid = bid;
    }

    public String getBdate() {
        return bdate;
    }

    public int getUid() {
        return uid;
    }

    public int getBid() {
        return bid;
    }

    public HashMap<Integer, RDBranchVO> getDetails() {
        return details;
    }

    public void addDetail(RDBranchVO rdBranchVO) {
        details.put(rdBranchVO.getTmpId(), rdBranchVO);
        rdBranchVO.setReserve(this);
    }
}
