package sharedByDifferentStyle;

import java.util.HashMap;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 * User basic info and some sale strategy for this user
 */
public class UserData {

    private static HashMap<String, String> users = new HashMap<>();
    public static Strategy defaultStrategy = new Strategy(0.9, 10);
    public static Strategy noDiscount = new Strategy(1, 0);

    static {
        users.put("tom", "1");
        users.put("tomy", "1");
    }

    public static boolean isUser(String name, String password) {
        return users.containsKey(name)
                && users.get(name).equals(password);
    }


}
