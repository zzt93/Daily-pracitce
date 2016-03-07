package mis;

/**
 * Created by zzt on 3/7/16.
 * <p>
 * Usage:
 */
public enum  SaleStatisticType {

    SALE_VOLUME("Sale volume"),
    SALE_AMOUNT("Sale amount");

    private final String des;

    public String getDes() {
        return des;
    }

    SaleStatisticType(String s) {
        this.des = s;
    }
}
