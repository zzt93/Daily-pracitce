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

    void addPlan(int bid, int did, int num);
    void deletePlan(int bid);
    void updatePlan(Plan Plan);
    ArrayList<Plan> allPlan();
}
