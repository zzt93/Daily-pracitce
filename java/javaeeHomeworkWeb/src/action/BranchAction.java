package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import entity.Reserve;
import entity.ReserveDetail;
import remote.JNDIFactory;
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
    private ReserveService reserveService;
    private final PlanService planService;


    public BranchAction() {
        reserveService = (ReserveService) JNDIFactory.getResource("");
        planService = (PlanService) JNDIFactory.getResource("");
    }

    @Override
    public String execute() throws Exception {
//        System.out.println("branch :" + branchNum);
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

    public String branchUserReserveList() throws Exception {
        int uid = SessionManagement.getUid();
        records = reserveService.branchUserReserve(branchNum, uid, jtStartIndex, jtPageSize);
        totalRecordCount = reserveService.countBranchUserReserve(branchNum, uid);
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String branchUserReserveDelete() throws Exception {
        reserveService.reserveDelete(rid);
        return SUCCESS;
    }


    /**
     * Finish an order
     * @return result string
     * @throws Exception
     */
    public String branchReservePay() throws Exception {
        // update reserve by adding detail
        Reserve reserve = reserveService.reserveGet(rid);
        HttpSession session = SessionManagement.getSession();
        reserve.setDetails((ArrayList<ReserveDetail>) session.getAttribute(ReserveDetailAction.RESERVE_DETAIL));
        reserveService.reserveAdd(reserve);
        // pay money
        return SUCCESS;
    }

}
