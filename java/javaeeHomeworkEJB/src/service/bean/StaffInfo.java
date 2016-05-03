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
    public boolean register(int bid, String pw, int type) {
        Branch branch = em.find(Branch.class, bid);
        if (branch == null) {
            return false;
        }
        em.persist(new Staff(branch, pw, type));
        return true;
    }

    @Override
    public boolean login(int sid, String pw, int type) {
        Staff staff = em.find(Staff.class, sid);
        return staff != null && staff.getPw().equals(pw) && staff.getType() == type;
    }

    @Override
    public Staff getStaff(int sid) {
        return em.find(Staff.class, sid);
    }

    @Override
    public int maxId() {
        Integer singleResult = em.createNamedQuery(Staff.MAX_ID, Integer.class).getSingleResult();
        if (singleResult == null) {
            return 0;
        }
        return singleResult;
    }
}
