package vo;

import entity.Branch;
import entity.Plan;
import entity.PlanDetail;

import java.util.ArrayList;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class PlanBranchVO {
    private final ArrayList<PlanDetail> details;
    private final String pdate;
    private final int planId;

    public PlanBranchVO(Plan plan, ArrayList<PlanDetail> details) {
        this.details = details;
        pdate = plan.getPdate();
        planId = plan.getPlanId();
    }

    public ArrayList<PlanDetail> getDetails() {
        return details;
    }

    public String getPdate() {
        return pdate;
    }

    public int getPlanId() {
        return planId;
    }
}
