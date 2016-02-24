package service;

import entity.Plan;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Remote
public interface PlanService {

    void addPlan(int bid, String planDate);
    void deletePlan(int planId);
    void updatePlan(Plan plan);
    ArrayList<Plan> newPlan();

    void addPlanDetail(int planId, int num, int did);
}
