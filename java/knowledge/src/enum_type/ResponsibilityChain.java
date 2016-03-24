package enum_type;

import java.util.Random;

/**
 * Created by zzt on 3/22/16.
 * <p>
 * Usage:
 */
public enum ResponsibilityChain {

    ME {
        @Override
        public boolean decide(int amount) {
            return amount < 100;
        }
    },
    BROTHER {
        @Override
        public boolean decide(int amount) {
            return amount < 500;
        }
    },
    DAD {
        @Override
        public boolean decide(int amount) {
            return amount < 1000;
        }
    },
    ;

    public abstract boolean decide(int amount);


    public static void main(String[] args) {
        int i = new Random().nextInt(1000);
        for (ResponsibilityChain chain : ResponsibilityChain.values()) {
            if (chain.decide(i)) {
                System.out.println(chain.name() + " decided");
                break;
            }
        }
    }
}
