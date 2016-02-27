package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import remote.JNDIFactory;
import service.PlanService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class PlanAction extends ActionSupport {

    private int planId;

    private PlanService planService = (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//PlanEJB!service.PlanService");

    private byte state;

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String planManagerUpdate() throws Exception {
        try {
            Plan plan = planService.getPlan(planId);
            plan.setState(state);
            planService.updatePlan(plan);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
        }
        return super.execute();
    }

    public String planDelete() throws Exception {
        try {
            planService.deletePlan(planId);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    private List<Plan> records;
    private String result;
    private String message;

    private int totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    public List<Plan> getRecords() {
        return records;
    }

    public void setRecords(List<Plan> records) {
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

    public String planManagerList() throws Exception {
        try {
            records = planService.newPlan();
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public String planStaffList() throws Exception {
        try {
            HttpSession session = SessionManagement.getSession();
            int sid = (int) session.getAttribute(InnerLogin.SID);
            records = planService.staffNotApprovedPlan(sid);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
        }
        return super.execute();
    }

    private int bid;
    private String pdate;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String planAdd() throws Exception {
        planService.addPlan(bid, pdate);
        return super.execute();
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String planDetailUpdate() throws Exception {
        return super.execute();
    }

    public String planDetailDelete() throws Exception {
        return super.execute();
    }

    public String planDetailList() throws Exception {
        return super.execute();
    }

}
