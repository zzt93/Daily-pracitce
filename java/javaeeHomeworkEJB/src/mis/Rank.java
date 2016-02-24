package mis;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
public enum Rank {
    LEVEL0(200, 1- 0.02, 20),
    LEVEL1(300, 1- 0.03, 30),
    LEVEL2(500, 1- 0.05, 50),
    ;

    private final int threshold;
    private final double ratio;
    private final int credit;

    Rank(int threshold, double ratio, int credit) {
        this.threshold = threshold;
        this.ratio = ratio;
        this.credit = credit;
    }

    public int getThreshold() {
        return threshold;
    }

    public double getRatio() {
        return ratio;
    }

    public int getCredit() {
        return credit;
    }
}
