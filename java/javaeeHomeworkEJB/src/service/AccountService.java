package service;

import entity.User;

import javax.ejb.Remote;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
@Remote
public interface AccountService {

    boolean register(String name, String pw);
    User getUser(int uid);

    void fillPrivateInfo(int uid, short age, byte gender);

    void fillPublicInfo(int uid, String name, String addr);

}
