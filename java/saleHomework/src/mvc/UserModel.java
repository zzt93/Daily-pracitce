package mvc;

import layered.bl.UserBL;
import sharedByDifferentStyle.User;

/**
 * Created by zzt on 1/22/16.
 * <p>
 * Usage:
 */
public class UserModel {
    private UserBL userBL = UserBL.getInstance();

    public User getUser(String name, String pw) {
        return userBL.getUser(name, pw);
    }
}
