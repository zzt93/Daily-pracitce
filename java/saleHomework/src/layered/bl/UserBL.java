package layered.bl;

import sharedByDifferentStyle.User;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class UserBL {

    private static UserBL user;

    public static UserBL getInstance() {
        if (user == null) {
            user = new UserBL();
        }
        return user;
    }

    private UserBL() {
    }

    public User getUser(String name, String pw) {
        return new User(name, pw);
    }
}
