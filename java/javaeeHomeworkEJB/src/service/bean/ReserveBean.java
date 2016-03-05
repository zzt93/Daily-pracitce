package service.bean;

import entity.*;
import service.ReserveService;
import service.exception.BalanceNotEnoughException;
import tmpEntity.RDBranchVO;
import tmpEntity.ReserveBranchVO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Reserve reserveAddAndPay(ReserveBranchVO reserveBranchVO) throws BalanceNotEnoughException {
        // pay money first
        Collection<RDBranchVO> values = reserveBranchVO.getDetails().values();
        double price = 0;
        for (RDBranchVO value : values) {
            price += value.getPrice() * value.getNum();
        }
        Consume consume = em.find(Consume.class, reserveBranchVO.getUid());
        if (consume == null) {
            return null;
        }
        Reserve entity;
        consume.payReservation(price);
        try {
            entity = new Reserve(
                    em.find(User.class, reserveBranchVO.getUid()),
                    em.find(Branch.class, reserveBranchVO.getBid()),
                    reserveBranchVO.getBdate()
            );
            em.persist(entity);
            for (RDBranchVO rdBranchVO : values) {
                Dessert dessert = em.find(Dessert.class, rdBranchVO.getDid());
                em.persist(new ReserveDetail(rdBranchVO.getNum(), rdBranchVO.getPrice(), entity, dessert));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    @Override
    public boolean reserveEdit(Reserve reserve) {
        try {
            em.merge(reserve);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        em.merge(reserve);
        return true;
    }

    @Override
    public Reserve reserveGet(int rid) {
        return em.find(Reserve.class, rid);
    }

    @Override
    public ArrayList<Reserve> userReserve(int uid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.USER_RESERVE, Reserve.class)
                .setParameter(1, uid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countUserReserve(int uid) {
        return (long) em.createNamedQuery(Reserve.COUNT_USER_RESERVE)
                .setParameter(1, uid)
                .getSingleResult();
    }

    @Override
    public ArrayList<Reserve> userPayment(int uid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.PAY_RESERVE, Reserve.class)
                .setParameter(1, uid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countUserPayment(int uid) {
        return (long) em.createNamedQuery(Reserve.COUNT_USER_PAYMENT)
                .setParameter(1, uid)
                .getSingleResult();
    }

    @Override
    public Reserve branchUserReserveDetail(int bid, int uid, String buyDate) {
        Reserve singleResult;
        try {
            singleResult = em.createNamedQuery(Reserve.BRANCH_USER_RESERVE_DETAIL, Reserve.class)
                    .setParameter(1, bid)
                    .setParameter(2, uid)
                    .setParameter(3, buyDate)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return singleResult;
    }

    @Override
    public List<ReserveDetail> reserveDetailGet(int rid) {
        return em.createNamedQuery(ReserveDetail.A_RESERVE_DETAIL, ReserveDetail.class)
                .setParameter(1, rid)
                .getResultList();
    }

    @Override
    public boolean reserveDetailDelete(int rdid) {
        ReserveDetail reserveDetail = em.find(ReserveDetail.class, rdid);
        if (reserveDetail == null) {
            return false;
        }
        em.remove(reserveDetail);
        return true;
    }

    @Override
    public ReserveDetail reserveDetailAdd(int rid, int did, int num, double price) {
        Reserve reserve = em.find(Reserve.class, rid);
        Dessert dessert = em.find(Dessert.class, did);
        if (reserve == null || dessert == null) {
            return null;
        }
        ReserveDetail entity = new ReserveDetail(num, price, reserve, dessert);
        em.persist(entity);
        return entity;
    }

    @Override
    public ReserveDetail reserveDetailUpdateNum(int rdid, int num) {
        ReserveDetail reserveDetail = em.find(ReserveDetail.class, rdid);
        if (reserveDetail == null) {
            return null;
        }
        reserveDetail.setNum(num);
        return reserveDetail;
    }

    @Override
    public ArrayList<Reserve> branchReserve(int bid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.BRANCH_RESERVE, Reserve.class)
                .setParameter(1, bid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countBranchReserve(int uid) {
        return (long) em.createNamedQuery(Reserve.COUNT_BRANCH_RESERVE)
                .setParameter(1, uid)
                .getSingleResult();
    }

    @Override
    public ArrayList<Reserve> branchUserReserve(int bid, int uid, int startIndex, int pageSize) {
        return (ArrayList<Reserve>) em.createNamedQuery(Reserve.BRANCH_USER_RESERVE, Reserve.class)
                .setParameter(1, bid)
                .setParameter(2, uid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countBranchUserReserve(int bid, int uid) {
        return (long) em.createNamedQuery(Reserve.COUNT_BRANCH_USER_RESERVE)
                .setParameter(1, bid)
                .setParameter(2, uid)
                .getSingleResult();
    }

}
