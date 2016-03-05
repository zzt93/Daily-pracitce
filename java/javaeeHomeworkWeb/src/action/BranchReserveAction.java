package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import entity.PlanDetail;
import entity.Reserve;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.PlanService;
import service.ReserveService;
import tmpEntity.ReserveBranchVO;
import vo.PlanBranchVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class BranchReserveAction extends ActionSupport {

    private int branchNum;
    private ArrayList<PlanBranchVO> plans = new ArrayList<>();
    private String branchAddr;


    public BranchReserveAction() {
    }

    @Override
    public String execute() throws Exception {
        PlanService planService =
                (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//PlanEJB!service.PlanService");
        assert planService != null;
        ArrayList<Plan> branchPlan = planService.branchPlan(branchNum);
        for (Plan plan : branchPlan) {
            ArrayList<PlanDetail> details = planService.getPlanDetails(plan.getPlanId());
            plans.add(new PlanBranchVO(plan, details));
        }
        if (branchPlan.size() > 0) {
            branchAddr = branchPlan.get(0).getBranch().getAddr();
        }
        return SUCCESS;
    }

    public String getBranchAddr() {
        return branchAddr;
    }

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public ArrayList<PlanBranchVO> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<PlanBranchVO> plans) {
        this.plans = plans;
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

    public long getTotalRecordCount() {
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

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String branchUserReserveList() throws Exception {
        int uid = SessionManagement.getUid();
        try {
            ReserveService reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
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
            reserveService = (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
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
    public String branchUserReservePay() throws Exception {
        // finish reserve by sending all data
        HttpSession session = SessionManagement.getSession();
        ReserveBranchVO tmpReserve = (ReserveBranchVO) session.getAttribute(SessionRDAction.TMP_RESERVE);
        session.setAttribute(SessionRDAction.TMP_RESERVE, null);
        try {
            ReserveService reserveService =
                    (ReserveService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//ReserveEJB!service.ReserveService");
            assert reserveService != null;
            Reserve reserve = reserveService.reserveAddAndPay(tmpReserve);
            if (reserve == null) {
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

}
