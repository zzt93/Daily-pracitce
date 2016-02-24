package service;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
public interface ConsumeService {

    boolean activateAccount(int uid, double money, String bank);
    boolean suspendAccount(int uid);
    boolean deleteAccount(int uid);

    boolean payMoney(int uid, double money);
}
