package service;

import entity.Plan;
import entity.PlanDetail;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Remote
public interface PlanService {

    Plan addPlan(int sid, int bid, String planDate);

    void deletePlan(int planId);
    void updatePlan(Plan plan);
    ArrayList<Plan> newPlan(int startIndex, int pageSize);
    long countNewPlan();

    ArrayList<Plan> branchPlan(int bid);

    ArrayList<Plan> staffNotApprovedPlan(int sid, int startIndex, int pageSize);

    Plan getPlan(int planId);

    ArrayList<PlanDetail> getPlanDetails(int planId);
    PlanDetail addPlanDetail(int planId, int did, double price, int num);
    void updatePlanDetail(int pdId, int did, int num);
    boolean deletePlanDetail(int pdId);

}
