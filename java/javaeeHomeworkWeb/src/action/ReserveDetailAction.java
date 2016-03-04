package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.ReserveDetail;
import remote.JNDIFactory;
import service.ReserveService;
import tmpEntity.RDBranchVO;
import tmpEntity.ReserveBranchVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public class ReserveDetailAction extends ActionSupport {

    // for table response
    private List<ReserveDetail> records = new ArrayList<>();
    private String result;
    private String message;
    private String bdate;
    private int bid;
    private ReserveDetail record;

    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
    }

    private int rdid;

    public List<ReserveDetail> getRecords() {
        return records;
    }

    public void setRecords(List<ReserveDetail> records) {
        this.records = records;
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

    private int rid;


    public String orderList() throws Exception {
        ReserveService reserveService =
                (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        List<ReserveDetail> reserveDetails = reserveService.reserveDetailGet(rid);
        for (ReserveDetail reserveDetail : reserveDetails) {
            records.add(reserveDetail);
        }
        result = JTableHelper.OK;
        return SUCCESS;
    }

    private int did;
    private int num;
    private double price;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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


    public String orderDelete() throws Exception {
        ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        assert reserveService != null;
        reserveService.reserveDetailDelete(rdid);
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String orderUpdate() throws Exception {
        if (num >= 1) {
            ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
            assert reserveService != null;
            record = reserveService.reserveDetailUpdateNum(rdid, num);
        }
        return SUCCESS;
    }
}
