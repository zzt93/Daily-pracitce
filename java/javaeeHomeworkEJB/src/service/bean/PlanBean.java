package service.bean;

import entity.*;
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
        ArrayList<Plan> resultList = (ArrayList<Plan>) em.createNamedQuery(Plan.STAFF_PLAN, Plan.class)
                .setParameter(1, sid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
        for (Plan plan : resultList) {
            Branch branch = plan.getBranch();
            branch.initLazy();
            Staff staff = plan.getStaff();
            staff.initLazy();
            for (PlanDetail planDetail : plan.getDetails()) {
                planDetail.getDessert().initLazy();
            }
        }
        return resultList;
    }

    @Override
    public Plan getPlan(int planId) {
        return em.find(Plan.class, planId);
    }

    @Override
    public PlanDetail addPlanDetail(int planId, int num, int did) {
        Plan plan = em.find(Plan.class, planId);
        Dessert dessert = em.find(Dessert.class, did);
        dessert.initLazy();
        PlanDetail entity = new PlanDetail(plan, dessert, num);
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
