package service.bean;

import entity.Branch;
import entity.Dessert;
import entity.Plan;
import entity.PlanDetail;
import service.PlanService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "PlanEJB")
public class PlanBean implements PlanService {

    @PersistenceContext
    EntityManager em;

    public PlanBean() {
    }

    @Override
    public void addPlan(int bid, String planDate) {
        em.persist(new Plan(em.find(Branch.class, bid), planDate));
    }

    @Override
    public void deletePlan(int planId) {
        em.remove(em.find(Plan.class, planId));
    }

    @Override
    public void updatePlan(Plan plan) {
        em.merge(plan);
    }

    @Override
    public ArrayList<Plan> newPlan(int startIndex, int pageSize) {
        return (ArrayList<Plan>) em.createNamedQuery(Plan.NEW_PLAN, Plan.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public ArrayList<Plan> branchPlan(int bid) {
        return (ArrayList<Plan>) em.createNamedQuery(Plan.BRANCH_PLAN, Plan.class)
                .setParameter(1, bid)
                .getResultList();
    }

    @Override
    public ArrayList<Plan> staffNotApprovedPlan(int sid, int startIndex, int pageSize) {
        return (ArrayList<Plan>) em.createNamedQuery(Plan.STAFF_PLAN, Plan.class)
                .setParameter(1, sid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public Plan getPlan(int planId) {
        return em.find(Plan.class, planId);
    }

    @Override
    public void addPlanDetail(int planId, int num, int did) {
        Plan plan = em.find(Plan.class, planId);
        Dessert dessert = em.find(Dessert.class, did);
        em.persist(new PlanDetail(plan, dessert, num));
    }

    @Override
    public void updatePlanDetail(int pdId, int did, int num) {
        PlanDetail planDetail = em.find(PlanDetail.class, pdId);
        planDetail.setNum(num);
        Dessert dessert = em.find(Dessert.class, did);
        planDetail.setDessert(dessert);
        em.merge(planDetail);
    }

    @Override
    public boolean deletePlanDetail(int pdId) {
        PlanDetail entity = em.find(PlanDetail.class, pdId);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

}
