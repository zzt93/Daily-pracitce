package mis;

import service.ConsumeService;

/**
 * Created by zzt on 2/26/16.
 * <p>
 * Usage:
 */
public enum  PayType {
    USER_CARD("user card balance") {
        @Override
        public boolean pay(int uid, double money, ConsumeService service) {
            return service.payMoney(uid, money);
        }
    },
    OTHER("cash or bankcard") {
        @Override
        public boolean pay(int uid, double money, ConsumeService service) {
            return false;
        }
    },
    ;

    private final String des;

    PayType(String s) {
        this.des = s;
    }

    public abstract boolean pay(int uid, double money, ConsumeService service);
}
