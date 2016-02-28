package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import entity.Reserve;
import remote.JNDIFactory;
import service.ConsumeService;
import service.PlanService;
import service.ReserveService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class BranchAction extends ActionSupport {

    private int branchNum;
    private ArrayList<Plan> plans;


    public BranchAction() {
    }

    @Override
    public String execute() throws Exception {
        PlanService planService =
                (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//PlanEJB!service.PlanService");
        assert planService != null;
        plans = planService.branchPlan(branchNum);
        return SUCCESS;
    }


    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    // for table response
    private List<Reserve> records;
    private String result;
    private String message;

    private int totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    public List<Reserve> getRecords() {
        return records;
    }

    public void setRecords(List<Reserve> records) {
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

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
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

    private int rid;
    private String buyDate;
    private double money;

    public String branchUserReserveList() throws Exception {
        int uid = SessionManagement.getUid();
        try {
            ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//ReserveEJB!service.ReserveService");
            records = reserveService.branchUserReserve(branchNum, uid, jtStartIndex, jtPageSize);
            totalRecordCount = reserveService.countBranchUserReserve(branchNum, uid);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String branchUserReserveDelete() throws Exception {
        ReserveService reserveService;
        try {
            reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//ReserveEJB!service.ReserveService");
            reserveService.reserveDelete(rid);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }


    /**
     * Finish an order
     *
     * @return result string
     *
     * @throws Exception
     */
    public String branchReservePay() throws Exception {
        // finish reserve by setting state
        HttpSession session = SessionManagement.getSession();
        session.setAttribute(ReserveDetailAction.RESERVE_START, false);
        int uid = SessionManagement.getUid();
        ReserveService reserveService =
                (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//ReserveEJB!service.ReserveService");
        assert reserveService != null;
        reserveService.reserveAdd(buyDate, uid, branchNum);
        // pay money
        ConsumeService consumeService;
        try {
            consumeService =
                    (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//UserInfoEJB!service.ConsumeService");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        assert consumeService != null;
        consumeService.payMoney(uid, money);
        return SUCCESS;
    }

}
