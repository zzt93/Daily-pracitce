package learnAspect;

/**
 * Created by zzt on 17/5/19.
 */
public class Account {
    int balance = 20;

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
