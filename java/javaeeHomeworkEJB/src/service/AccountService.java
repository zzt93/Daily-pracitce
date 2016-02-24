package service;

import javax.ejb.Remote;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
@Remote
public interface AccountService {

    boolean register(String name, String pw);

    void fillPrivateInfo(int uid, short age, byte gender);

    void fillPublicInfo(int uid, String name, String addr);

}
