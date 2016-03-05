package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Reserve;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.ReserveService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class AccountReserveAction extends ActionSupport {

    private final ReserveService reserveService;

    public AccountReserveAction() {
        reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
    }

    private int branchNum;

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    private int rid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    // for table response
    private List<Reserve> records;
    private String result;
    private String message;

    private long totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

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

    public List<Reserve> getRecords() {
        return records;
    }

    public void setRecords(List<Reserve> records) {
        this.records = records;
    }

    public int getJtStartIndex() {
        return jtStartIndex;
    }

    public void setJtStartIndex(int jtStartIndex) {
        this.jtStartIndex = jtStartIndex;
    }

    public int getJtPageSize() {
        return jtPageSize;
    }

    public void setJtPageSize(int jtPageSize) {
        this.jtPageSize = jtPageSize;
    }

    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String userReserveList() throws Exception {
        HttpSession session = SessionManagement.getSession();
        Integer uid = (Integer) session.getAttribute(UserLogin.UID);
        records =
                reserveService.userReserve(uid, jtStartIndex, jtPageSize);
        totalRecordCount = reserveService.countUserReserve(uid);
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String userReserveDelete() throws Exception {
        reserveService.reserveDelete(rid);
        result = JTableHelper.OK;
        return SUCCESS;
    }

}
