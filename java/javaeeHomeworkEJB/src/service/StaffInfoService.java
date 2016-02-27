package service;

import entity.Staff;

import javax.ejb.Remote;

/**
 * Created by zzt on 2/22/16.
 * <p>
 * Usage:
 */
@Remote
public interface StaffInfoService {

    boolean register(int bid, String pw, int type);
    boolean login(int sid, String pw, int type);

    Staff getStaff(int sid);
    int maxId();

}
