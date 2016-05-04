package service.bean;

import entity.*;
import mis.ChartDataWrapper;
import mis.Default;
import mis.ReservationState;
import mis.SaleStatisticType;
import service.ReserveService;
import service.exception.BalanceNotEnoughException;
import tmpEntity.RDBranchVO;
import tmpEntity.ReserveBranchVO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
        // check plan detail
        HashMap<Integer, PlanDetail> map = new HashMap<>();
        TypedQuery<PlanDetail> query = em.createNamedQuery(PlanDetail.DETAIL_BY_BID_DATE_DID, PlanDetail.class)
                .setParameter(1, reserveBranchVO.getBid())
                .setParameter(2, reserveBranchVO.getBdate());
        for (RDBranchVO rdBranchVO : reserveBranchVO.getDetails().values()) {
            int did = rdBranchVO.getDid();
            // TODO: 5/4/16 duplicate goods make crash ?
            PlanDetail detail = query.setParameter(3, did).getSingleResult();
            map.put(did, detail);
            Integer planNum = detail.getNum();
            if (planNum < rdBranchVO.getNum()) {
                return null;
            }
        }
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
        // update reservation detail and plan detail
        try {
            entity = new Reserve(
                    em.find(User.class, reserveBranchVO.getUid()),
                    em.find(Branch.class, reserveBranchVO.getBid()),
                    reserveBranchVO.getBdate()
            );
            em.persist(entity);
            for (RDBranchVO rdBranchVO : values) {
                int did = rdBranchVO.getDid();
                PlanDetail planDetail = map.get(did);
                planDetail.setNum(planDetail.getNum() - rdBranchVO.getNum());
                em.persist(planDetail);
                Dessert dessert = em.find(Dessert.class, did);
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
    public HashMap<Boolean, long[]> reserveCountByBranch() {
        List<Branch> branches =
                em.createNamedQuery(Branch.ALL_BRANCH, Branch.class).getResultList();
        HashMap<Boolean, long[]> res = new HashMap<>();
        for (ReservationState reservationState : ReservationState.values()) {
            res.put(reservationState.getState(), new long[branches.size()]);
        }
        for (int i = 0; i < branches.size(); i++) {
            Branch branch = branches.get(i);
            List<Object[]> resultList = em.createNamedQuery(Reserve.RESERVE_COUNT_BY_BRANCH, Object[].class)
                    .setParameter(1, branch.getBid())
                    .getResultList();
            for (Object[] objects : resultList) {
                Boolean state = (Boolean) objects[0];
                if (state == null) {
                    break;
                }
                long[] count = res.get(state);
                count[i] = (long) objects[1];
            }
        }
        return res;
    }

    @Override
    public ChartDataWrapper saleSumByDessert() {
        //select did, sum(did * price) s from rdetail group by did order by s desc;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<ReserveDetail> rd = query.from(ReserveDetail.class);
        Expression<? extends Number> sum = builder.sum(builder.prod(rd.<Integer>get("num"), rd.<Double>get("price")));
        Path<Integer> didPath = rd.<Dessert>get("dessert").<Integer>get("did");
        query.multiselect(didPath, sum);
        query.groupBy(didPath);
        query.orderBy(builder.desc(sum));

        List<Object[]> resultList = em.createQuery(query)
                .setMaxResults(Default.CAKE_ORDRE_LIMIT)
                .getResultList();
        HashMap<Integer, double[]> res = new HashMap<>();
        for (SaleStatisticType saleStatisticType : SaleStatisticType.values()) {
            res.put(saleStatisticType.ordinal(), new double[Default.CAKE_ORDRE_LIMIT]);
        }
        int[] labels = new int[Default.CAKE_ORDRE_LIMIT];
        for (int i = 0; i < resultList.size(); i++) {
            Object[] objects = resultList.get(i);
            Integer did = (Integer) objects[0];
            labels[i] = did;
            double volumeSum = (double) objects[1];
            Long count = em.createNamedQuery(ReserveDetail.SUM_AMOUNT_BY_DESSERT, Long.class)
                    .setParameter(1, did)
                    .getSingleResult();
            res.get(SaleStatisticType.SALE_VOLUME.ordinal())[i] = volumeSum;
            res.get(SaleStatisticType.SALE_AMOUNT.ordinal())[i] = count;
        }
        return new ChartDataWrapper(labels, res);
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
