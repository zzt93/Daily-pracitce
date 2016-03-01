package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import remote.JNDIFactory;
import service.PlanService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class PlanAction extends ActionSupport {

    private int planId;

    private PlanService planService = (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//PlanEJB!service.PlanService");

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
            plan.setPlanState(state);
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

    private Plan record;

    private List<Plan> records;
    private String result;
    private String message;

    private int totalRecordCount;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;

    public Plan getRecord() {
        return record;
    }

    public void setRecord(Plan record) {
        this.record = record;
    }

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
            records = planService.newPlan(jtStartIndex, jtPageSize);
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
            records = planService.staffNotApprovedPlan(sid, jtStartIndex, jtPageSize);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
            message = "...";
            return ERROR;
        }
        return SUCCESS;
    }

    private int branch;
    private String pdate;

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String planAdd() throws Exception {
        try {
            int sid = (int) SessionManagement.getSession().getAttribute(InnerLogin.SID);
            Plan plan = planService.addPlan(sid, branch, pdate);
            if (plan != null) {
                result = JTableHelper.OK;
                record = plan;
            } else {
                result = JTableHelper.ERROR;
                message = "Already have a plan for this branch at " + pdate;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
            result = JTableHelper.ERROR;
            return ERROR;
        }
        return SUCCESS;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }






}
