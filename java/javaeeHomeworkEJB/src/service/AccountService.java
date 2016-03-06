package service;

import entity.User;

import javax.ejb.Remote;
import java.util.HashMap;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
@Remote
public interface AccountService {

    User login(String name, String pw);
    User register(String name, String pw);
    User getUser(int uid);


    void fillPrivateInfo(int uid, short age, byte gender);

    void fillPublicInfo(int uid, String name, String addr);

    HashMap<Byte, long[]> accountCountByGenderAndAge(short[] ageRanges);
}
