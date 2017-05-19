package learnAspect;

/**
 * Created by zzt on 17/5/19.
 */
public aspect LearnAspect {

    final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account acc) :
            call(boolean Account.withdraw(int)) && args(amount) && target(acc);
    //following two will not work, because primitives and boxing type is different
//            call(Boolean Account.withdraw(int)) && args(amount) && target(acc);
//            call(Boolean Account.withdraw(Integer)) && args(amount) && target(acc);

    before(int amount, Account acc) : callWithDraw(amount, acc) {
        System.err.println("before call");
    }

    boolean around(int amount, Account acc) :
            callWithDraw(amount, acc) {
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance) : callWithDraw(amount, balance) {
        System.err.println("after call");
    }

}
