package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.PlanService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class PlanAction extends ActionSupport {

    private int planId;

    private PlanService planService = (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//PlanEJB!service.PlanService");

    private byte planState;

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public byte getPlanState() {
        return planState;
    }

    public void setPlanState(byte planState) {
        this.planState = planState;
    }

    public String planManagerUpdate() throws Exception {
        try {
            record = planService.managerUpdatePlan(planId, planState);
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

    public String planWaiterUpdate() throws Exception {
        if (checkPlanDate()) {
            return ERROR;
        }
        try {
            record = planService.staffUpdatePlan(planId, branch, pdate, planState);
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

    private long totalRecordCount;
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

    public String planManagerList() throws Exception {
        try {
            records = planService.newPlan(jtStartIndex, jtPageSize);
            totalRecordCount = planService.countNewPlan();
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
            message = e.getMessage();
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
        if (checkPlanDate()) {
            return ERROR;
        }
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

    private boolean checkPlanDate() {
        if (pdate.compareTo(LocalDate.now().toString()) < 0) {
            message = "invalid date";
            result = JTableHelper.ERROR;
            return true;
        }
        return false;
    }

    //    public String planApprove() throws Exception {
//        try {
//            Plan plan = null;
//            result = JTableHelper.OK;
//            record = plan;
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = e.getMessage();
//            result = JTableHelper.ERROR;
//            return ERROR;
//        }
//        return SUCCESS;
//    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }


}
