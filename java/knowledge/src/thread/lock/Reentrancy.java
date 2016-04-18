package thread.lock;

import net.jcip.annotations.Immutable;
/**
 * Created by zzt on 4/17/16.
 * <p>
 * Usage:
 */
class Widget {
    public synchronized void doSomething() {
        System.out.println(this.getClass() + " do something");
    }
}

public class Reentrancy extends Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println("call father's doSomething");
        super.doSomething();
    }

    public static void main(String[] args) {
        new Reentrancy().doSomething();
    }
}
