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
    public Branch addBranch(String addr) {
        Branch entity = new Branch(addr);
        em.persist(entity);
        return entity;
    }

    @Override
    public void deleteBranch(int bid) {
        em.remove(em.find(Branch.class, bid));
    }

    @Override
    public Branch updateBranch(int bid, String addr) {
        Branch branch = em.find(Branch.class, bid);
        if (branch == null) {
            return null;
        }
        branch.setAddr(addr);
        em.merge(branch);
        return branch;
    }

    @Override
    public ArrayList<Branch> allBranch() {
        return (ArrayList<Branch>) em.createNamedQuery(Branch.ALL_BRANCH, Branch.class).getResultList();
    }
}
