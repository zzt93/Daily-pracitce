package service.bean;

import entity.Account;
import entity.Consume;
import entity.User;
import mis.CardState;
import mis.Gender;
import mis.Rank;
import service.AccountService;
import service.ConsumeService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "UserInfoEJB")
public class AccountBean implements AccountService, ConsumeService {

    @PersistenceContext
    private EntityManager em;

    public AccountBean() {
    }

    /**
     * @param name user name
     * @param pw   Password
     *
     * @return user id if exist such user and password is right
     */
    @Override
    public User login(String name, String pw) {
        User user;
        try {
            user = em.createNamedQuery(User.FIND_USER_BY_NAME, User.class)
                    .setParameter(1, name).getSingleResult();
        } catch (Exception e) {
            //            e.printStackTrace();
            return null;
        }
        if (user.getPw().equals(pw)) {
            return user;
        }
        return null;
    }

    /**
     * @param name user name
     * @param pw   password
     *
     * @return user object if no such name has already used;
     * Otherwise, return null
     */
    @Override
    public User register(String name, String pw) {
        User user;
        try {
            em.createNamedQuery(User.FIND_USER_BY_NAME, User.class)
                    .setParameter(1, name).getSingleResult();
        } catch (NoResultException e) {
            em.persist(new User(name, pw));
            user = em.createNamedQuery(User.FIND_USER_BY_NAME, User.class)
                    .setParameter(1, name).getSingleResult();
            Account account = new Account(user);
            Consume consume = new Consume(user);
            em.persist(account);
            em.persist(consume);
            return user;
        }
        return null;

    }

    @Override
    public User getUser(int uid) {
        return em.find(User.class, uid);
    }

    @Override
    public void fillPrivateInfo(int uid, short age, byte gender) {
        Account account = em.find(Account.class, uid);
        if (account == null) {
            account = new Account();
        }
        account.setAge(age);
        account.setGender(gender);
        em.persist(account);
    }

    @Override
    public void fillPublicInfo(int uid, String name, String addr) {
        User user = em.find(User.class, uid);
        user.setName(name);
        Account account = user.getAccount();
        if (account == null) {
            account = new Account();
        }
        account.setAddr(addr);
        em.persist(account);
    }

    @Override
    public HashMap<Byte, long[]> accountCountByGenderAndAge(short[] ageRanges) {
        HashMap<Byte, long[]> res = new HashMap<>(Gender.values().length);
        for (Gender gender : Gender.values()) {
            long[] counts = new long[ageRanges.length - 1];
            res.put((byte) gender.ordinal(), counts);
        }
        for (int i = 0; i < ageRanges.length - 1; i++) {
            List<Object[]> resultList = em.createNamedQuery(Account.ACCOUNT_COUNT_GENDER_AGE, Object[].class)
                    .setParameter(1, ageRanges[i])
                    .setParameter(2, ageRanges[i + 1])
                    .getResultList();
            for (Object[] objects : resultList) {
                long count = (long) objects[1];
                Byte gender = (Byte) objects[0];
                long[] counts = res.get(gender);
                counts[i] = count;
            }
        }
        return res;
    }


    @Override
    public boolean activateAccount(int uid, double money, String bank) {
        Account account = em.find(Account.class, uid);
        Consume consume = em.find(Consume.class, uid);
        account.setBankCard(bank);
        // set rank
        byte rank1 = (byte) Rank.values()[Rank.values().length - 1].ordinal();
        for (Rank rank : Rank.values()) {
            if (rank.getThreshold() > money) {
                rank1 = (byte) (rank.ordinal() - 1);
                break;
            }
        }
        if (rank1 > consume.getRank()) {
            consume.setRank(rank1);
        }
        // set card state
        CardState cardState = CardState.moveNext(consume.getState());
        consume.setState((byte) cardState.ordinal());
        // set balance
        double balance = consume.getBalance();
        consume.setBalance(money + balance);
        try {
            em.merge(consume);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean suspendAccount(int uid) {
        setState(uid, (byte) CardState.SUSPEND.ordinal());
        return true;
    }

    private void setState(int uid, byte ordinal) {
        Consume consume = em.find(Consume.class, uid);
        consume.setState(ordinal);
    }

    @Override
    public boolean deleteAccount(int uid) {
        setState(uid, (byte) CardState.CANCEL.ordinal());
        return true;
    }

    @Override
    public boolean payMoney(int uid, double money) {
        return addBalanceAndCredit(uid, -money);
    }

    @Override
    public List<Consume> userBalanceList() {
        return em.createNamedQuery(Consume.OWNING_MONEY_USER, Consume.class).getResultList();
    }

    @Override
    public List<Consume> userCardList() {
        return em.createNamedQuery(Consume.ALL_CONSUME, Consume.class).getResultList();
    }

    @Override
    public long countUserBalanceList() {
        return (long) em.createNamedQuery(Consume.COUNT_OWNING_MONEY_USER).getSingleResult();
    }

    @Override
    public long countuserCardList() {
        return (long) em.createNamedQuery(Consume.COUNT_ALL_CONSUME).getSingleResult();
    }

    private boolean addBalanceAndCredit(int uid, double money) {
        Consume consume = em.find(Consume.class, uid);
        if (consume.getState() == CardState.SUSPEND.ordinal()) {
            return false;
        }
        double balance = consume.getBalance();
        if (balance + money < 0) {
            return false;
        }
        consume.setBalance(money + balance);
        consume.setCredit((int) money + consume.getCredit());
        em.merge(consume);
        return true;
    }
}
