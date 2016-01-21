package sharedByDifferentStyle;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class User {
    private Strategy strategy;
    public User(String name, String pw) {
        if (UserData.isUser(name, pw)) {
            strategy = UserData.defaultStrategy;
        } else {
            strategy = UserData.noDiscount;
        }
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
