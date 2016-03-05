package service;

import entity.Consume;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
@Remote
public interface ConsumeService {

    boolean activateAccount(int uid, double money, String bank);
    boolean suspendAccount(int uid);
    boolean deleteAccount(int uid);

    boolean payMoney(int uid, double money);

    List<Consume> userBalanceList();
    List<Consume> userCardList();
    long countUserBalanceList();
    long countuserCardList();
}
