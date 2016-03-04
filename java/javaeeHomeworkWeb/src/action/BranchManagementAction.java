package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Branch;
import remote.JNDIFactory;
import service.BranchService;

import java.util.List;

/**
 * Created by zzt on 3/2/16.
 * <p>
 * Usage:
 */
public class BranchManagementAction extends ActionSupport {

    private final BranchService branchService;

    public BranchManagementAction() {
        branchService = (BranchService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//BranchEJB!service.BranchService");
    }

    private List<Branch> records;
    private Branch record;
    private String result;
    private String message;

    public List<Branch> getRecords() {
        return records;
    }

    public void setRecords(List<Branch> records) {
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

    public Branch getRecord() {
        return record;
    }

    public void setRecord(Branch record) {
        this.record = record;
    }

    public String branchList() throws Exception {
        try {
            records = branchService.allBranch();
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    int bid;
    private String addr;

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String branchDelete() throws Exception {
        try {
            branchService.deleteBranch(bid);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public String branchUpdate() throws Exception {
        try {
            record = branchService.updateBranch(bid, addr);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public String branchAdd() throws Exception {
        try {
            record = branchService.addBranch(addr);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }
}
