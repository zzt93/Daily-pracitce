package sharedByDifferentStyle;

/**
 * Created by zzt on 1/21/16.
 * <p>
 * Usage:
 */
public class Strategy {

    private double discount;
    private double sale;

    public Strategy(double discount, double sale) {
        this.discount = discount;
        this.sale = sale;
    }

    public double afterStrategy(double total) {
        if (total < 50) {
            return total;
        }
        return total * discount - sale;
    }
}
