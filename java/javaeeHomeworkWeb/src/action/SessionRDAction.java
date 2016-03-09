package action;

import com.opensymphony.xwork2.ActionSupport;
import interceptor.SessionManagement;
import tmpEntity.RDBranchVO;
import tmpEntity.ReserveBranchVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class SessionRDAction extends ActionSupport {

    public static final String TMP_RESERVE = "reserveDetail";
    public static final String RESERVE_DETAIL_COUNT = "reserve detail count";
    private String bdate;
    private int bid;
    private int did;
    private int num;
    private double price;
    private int tmpId;

    private String result;
    private String message;
    private ArrayList<RDBranchVO> records = new ArrayList<>();
    private RDBranchVO record;
    private String dessertName;

    public String getDessertName() {
        return dessertName;
    }

    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTmpId() {
        return tmpId;
    }

    public void setTmpId(int tmpId) {
        this.tmpId = tmpId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<RDBranchVO> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RDBranchVO> records) {
        this.records = records;
    }

    public RDBranchVO getRecord() {
        return record;
    }

    public void setRecord(RDBranchVO record) {
        this.record = record;
    }

    public String orderBranchList() throws Exception {
        HttpSession session = SessionManagement.getSession();
        ReserveBranchVO tmpReserve = (ReserveBranchVO) session.getAttribute(TMP_RESERVE);
        if (tmpReserve != null) {
            records.addAll(tmpReserve.getDetails().values());
        }
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String orderBranchAdd() throws Exception {
        HttpSession session = SessionManagement.getSession();
        ReserveBranchVO tmpReserve = (ReserveBranchVO) session.getAttribute(TMP_RESERVE);
        int count;
        if (tmpReserve == null || tmpReserve.getDetails().size() == 0) {
            tmpReserve = new ReserveBranchVO(bdate, (int) session.getAttribute(UserLogin.UID), bid);
            count = 0;
            session.setAttribute(TMP_RESERVE, tmpReserve);
            session.setAttribute(RESERVE_DETAIL_COUNT, count);
        } else {
            count = (int) session.getAttribute(RESERVE_DETAIL_COUNT);
        }
        tmpReserve.addDetail(new RDBranchVO(count++, did, dessertName, num, price));
        session.setAttribute(RESERVE_DETAIL_COUNT, count);
        tmpId = count - 1;
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String orderBranchDelete() throws Exception {
        HttpSession session = SessionManagement.getSession();
        ReserveBranchVO tmpReserve = (ReserveBranchVO) session.getAttribute(TMP_RESERVE);
        RDBranchVO remove = tmpReserve.getDetails().remove(tmpId);
        if (remove == null) {
            return ERROR;
        }
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String orderBranchUpdate() throws Exception {
        if (num >= 1) {
            HttpSession session = SessionManagement.getSession();
            ReserveBranchVO tmpReserve = (ReserveBranchVO) session.getAttribute(TMP_RESERVE);
            RDBranchVO rdBranchVO = tmpReserve.getDetails().get(tmpId);
            rdBranchVO.setNum(num);
            record = rdBranchVO;
            result = JTableHelper.OK;
            return SUCCESS;
        } else {
            message = "wrong amount to update";
            result = JTableHelper.ERROR;
            return ERROR;
        }
    }

}
