package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Reserve;
import remote.JNDIFactory;
import service.ReserveService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class ReserveAction extends ActionSupport {

    private final ReserveService reserveService;

    public ReserveAction() {
        reserveService = (ReserveService) JNDIFactory.getResource("");
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    private int branchNum;

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public String branchReservePay() throws Exception {
        return SUCCESS;
    }

    private String bdate;
    private int did;
    private int num;
    private double price;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String branchReserveAdd() throws Exception {
        return SUCCESS;
    }

    public String branchReserveEdit() throws Exception {
        return SUCCESS;
    }

    public String branchReserveDelete() throws Exception {
        return SUCCESS;
    }

    // for table response
    private List<entity.Reserve> records;
    private String result;
    private String message;

    private int totalRecordCount;
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

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String userReserveList() throws Exception {
        HttpSession session = SessionManagement.getSession();
        records =
                reserveService.userReserve((Integer) session.getAttribute(UserLogin.UID), jtStartIndex, jtPageSize);
        result = JTableHelper.OK;
        message = "this fine";
        totalRecordCount = 16;
//        records = students.subList(jtStartIndex, jtStartIndex + jtPageSize);
        return execute();
    }

    public String userReserveDelete() throws Exception {
        result = JTableHelper.OK;
        return SUCCESS;
    }
}
