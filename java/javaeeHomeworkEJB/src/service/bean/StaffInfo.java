package service.bean;

import entity.Branch;
import entity.Staff;
import service.StaffInfoService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zzt on 2/22/16.
 * <p>
 * Usage:
 */
@Stateless(name = "StaffEJB")
public class StaffInfo implements StaffInfoService {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean register(int bid, String pw) {
        Branch branch = em.find(Branch.class, bid);
        if (branch == null) {
            return false;
        }
        em.persist(new Staff(branch, pw));
        return true;
    }
}
