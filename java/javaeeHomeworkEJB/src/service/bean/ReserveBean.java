package service.bean;

import entity.Branch;
import entity.Reserve;
import entity.User;
import service.ReserveService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "ReserveEJB")
public class ReserveBean implements ReserveService {

    @PersistenceContext
    EntityManager em;

    public ReserveBean() {
    }


    @Override
    public boolean reserveAdd(int uid, int bid, String bdate) {
        User user = em.find(User.class, uid);
        Branch branch = em.find(Branch.class, bid);
        em.persist(new Reserve(user, branch, bdate));
        return true;
    }

    @Override
    public boolean reserveEdit(Reserve reserve) {
        em.persist(reserve);
        return true;
    }

    @Override
    public boolean reserveDelete(int rid) {
        em.remove(em.find(Reserve.class, rid));
        return true;
    }

    @Override
    public boolean reserveFinish(int rid) {
        Reserve reserve = em.find(Reserve.class, rid);
        reserve.setState(true);
        em.persist(reserve);
        return true;
    }

    @Override
    public ArrayList<Reserve> userReserve(int uid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.BRANCH_RESERVE, Reserve.class)
                .setParameter(1, uid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public ArrayList<Reserve> branchReserve(int bid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.USER_RESERVE, Reserve.class)
                .setParameter(1, bid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

}
