package service.bean;

import entity.Branch;
import service.BranchService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "BranchEJB")
public class BranchBean implements BranchService{

    @PersistenceContext
    EntityManager em;

    public BranchBean() {
    }


    @Override
    public void addBranch(String addr) {
        em.persist(new Branch(addr));
    }

    @Override
    public void deleteBranch(int bid) {
        em.remove(em.find(Branch.class, bid));
    }

    @Override
    public void updateBranch(Branch branch) {
        em.persist(branch);
    }

    @Override
    public ArrayList<Branch> allBranch() {
        return (ArrayList<Branch>) em.createNamedQuery(Branch.ALL_BRANCH).getResultList();
    }
}
