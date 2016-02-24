package service.bean;

import entity.Plan;
import service.PlanService;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "PlanEJB")
public class PlanBean implements PlanService{
    public PlanBean() {
    }

    @Override
    public void addPlan(int bid, int did, int num) {

    }

    @Override
    public void deletePlan(int bid) {

    }

    @Override
    public void updatePlan(Plan Plan) {

    }

    @Override
    public ArrayList<Plan> allPlan() {
        return null;
    }
}
