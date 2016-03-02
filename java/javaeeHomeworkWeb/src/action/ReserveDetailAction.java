package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Reserve;
import entity.ReserveDetail;
import remote.JNDIFactory;
import service.ReserveService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public class ReserveDetailAction extends ActionSupport {

    public static final String RESERVE_START = "reserveDetail";
    // for table response
    private List<ReserveDetail> records;
    private String result;
    private String message;

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

    private int rid;

    /**
     * Always empty at the beginning for it represent the new order of
     * a user in this branch
     * @return result
     * @throws Exception
     */
    public String orderBranchList() throws Exception {
        records = new ArrayList<>();
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String orderList() throws Exception {
        ReserveService reserveService =
                (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        List<ReserveDetail> reserveDetails = reserveService.reserveDetailGet(rid);
        records.addAll(reserveDetails.stream().collect(Collectors.toList()));
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

    public String orderBranchAdd() throws Exception {
        ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
        HttpSession session = SessionManagement.getSession();
        boolean start = (boolean) session.getAttribute(RESERVE_START);
        if (!start) {
            session.setAttribute(RESERVE_START, true);
            assert reserveService != null;
        }
        assert reserveService != null;
        reserveService.reserveDetailAdd(rid, did, num, price);
        result = JTableHelper.OK;
        return SUCCESS;
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
            reserveService.reserveDetailUpdateNum(rdid, num);
        }
        return SUCCESS;
    }
}
