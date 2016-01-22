package sharedByDifferentStyle;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class User {
    private Strategy strategy;
    private String name;
    private String pw;

    public User(String name, String pw) {
        this.name = name;
        this.pw = pw;
        if (UserData.isUser(name, pw)) {
            strategy = UserData.defaultStrategy;
            System.out.println("welcome " + name);
        } else {
            strategy = UserData.noDiscount;
            System.out.println("welcome a new guest");
        }
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public double afterStrategy(double total) {
        return strategy.afterStrategy(total);
    }

    public String inBill() {
        return "bill of " + name;
    }
}
