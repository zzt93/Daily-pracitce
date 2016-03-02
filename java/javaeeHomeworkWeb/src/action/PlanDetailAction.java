package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Plan;
import entity.PlanDetail;
import remote.JNDIFactory;
import service.PlanService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/28/16.
 * <p>
 * Usage:
 *
 */
public class PlanDetailAction extends ActionSupport {

    private PlanService planService = (PlanService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//PlanEJB!service.PlanService");
    private int planId;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }


    private List<PlanDetail> records = new ArrayList<>();
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
        ArrayList<PlanDetail> details = planService.getPlanDetails(planId);
        for (PlanDetail planDetail : details) {
            records.add(planDetail);
        }
        result = JTableHelper.OK;
        return super.execute();
    }

    private int pdId;
    private int dessert;
    private double price;
    private int num;


    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public String planDetailUpdate() throws Exception {
        planService.updatePlanDetail(pdId, dessert, num);
        result = JTableHelper.OK;
        return SUCCESS;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDessert() {
        return dessert;
    }

    public void setDessert(int dessert) {
        this.dessert = dessert;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private PlanDetail record;

    public PlanDetail getRecord() {
        return record;
    }

    public void setRecord(PlanDetail record) {
        this.record = record;
    }

    public String planDetailAdd() throws Exception {
        if (dessert == 0) {
            result = JTableHelper.ERROR;
            return ERROR;
        }
        try {
            record = planService.addPlanDetail(planId, dessert, price, num);
            result = JTableHelper.OK;
        } catch (Exception e) {
            e.printStackTrace();
            result = JTableHelper.ERROR;
        }
        return SUCCESS;
    }

    public String planDetailDelete() throws Exception {
        planService.deletePlanDetail(pdId);
        return SUCCESS;
    }
}
