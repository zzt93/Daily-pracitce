package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import entity.PlanDetail;
import remote.JNDIFactory;
import service.PlanService;

import java.util.List;

/**
 * Created by zzt on 2/28/16.
 * <p>
 * Usage:
 */
public class PlanDetailAction extends ActionSupport {

    private PlanService planService = (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//PlanEJB!service.PlanService");
    private int planId;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }


    private List<PlanDetail> records;
    private String result;
    private String message;

    public List<PlanDetail> getRecords() {
        return records;
    }

    public void setRecords(List<PlanDetail> records) {
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

    public String planDetailList() throws Exception {
        Plan plan = planService.getPlan(planId);
        for (PlanDetail planDetail : plan.getDetails()) {
            records.add(planDetail);
        }
        result = JTableHelper.OK;
        return super.execute();
    }
}
