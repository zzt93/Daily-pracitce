package service.bean;

import entity.*;
import service.PlanService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
    public Plan addPlan(int sid, int bid, String planDate) {
        try {
            em.createNamedQuery(Plan.SAME_PLAN, Plan.class)
                    .setParameter(1, bid)
                    .setParameter(2, planDate)
                    .getSingleResult();
        } catch (Exception e) {
            Plan entity = new Plan(em.find(Staff.class, sid), em.find(Branch.class, bid), planDate);
            em.persist(entity);
            return entity;
        }
        return null;
    }

    @Override
    public void deletePlan(int planId) {
        em.remove(em.find(Plan.class, planId));
    }

    @Override
    public Plan managerUpdatePlan(int planId, byte planState) {
        Plan plan = em.find(Plan.class, planId);
        plan.setPlanState(planState);
        em.merge(plan);
        return plan;
    }

    @Override
    public Plan staffUpdatePlan(int planId, int bid, String pdate, byte planState) {
//        Branch branch = em.find(Branch.class, bid);
//        if (branch == null) {
//            return null;
//        }
//        plan.setBranch(branch);
        Plan plan = em.find(Plan.class, planId);
        plan.setPdate(pdate);
        plan.setPlanState(planState);
        em.merge(plan);
        return plan;
    }

    @Override
    public ArrayList<Plan> newPlan(int startIndex, int pageSize) {
        return (ArrayList<Plan>) em.createNamedQuery(Plan.NEW_PLAN, Plan.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countNewPlan() {
        return (long) em.createNamedQuery(Plan.COUNT_NEW_PLAN).getSingleResult();
    }

    @Override
    public ArrayList<Plan> branchPlan(int bid) {
        return (ArrayList<Plan>) em.createNamedQuery(Plan.BRANCH_PLAN, Plan.class)
                .setParameter(1, bid)
                .setParameter(2, LocalDate.now().toString())
                .getResultList();
    }

    @Override
    public ArrayList<Plan> staffNotApprovedPlan(int sid, int startIndex, int pageSize) {
        ArrayList<Plan> resultList = (ArrayList<Plan>) em.createNamedQuery(Plan.STAFF_PLAN, Plan.class)
                .setParameter(1, sid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
//        for (Plan plan : resultList) {
//            plan.initLazy();
//        }
        return resultList;
    }

    @Override
    public Plan getPlan(int planId) {
        Plan plan = em.find(Plan.class, planId);
//        plan.initLazy();
        return plan;
    }

    @Override
    public ArrayList<PlanDetail> getPlanDetails(int planId) {
        return (ArrayList<PlanDetail>) em.createNamedQuery(PlanDetail.A_PLAN_DETAIL, PlanDetail.class)
                .setParameter(1, planId)
                .getResultList();
    }

    @Override
    public PlanDetail addPlanDetail(int planId, int did, double price, int num) {
        Plan plan = em.find(Plan.class, planId);
        Dessert dessert = em.find(Dessert.class, did);
        PlanDetail entity = new PlanDetail(plan, dessert, price, num);
        /*
        have to maintain the balance of parent and child, for you get
        plan first, and add a new plan detail, you have to add detail by yourself
        plan.addDetail(entity);
         */
        try {
            em.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
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
