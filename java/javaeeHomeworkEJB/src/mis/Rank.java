package mis;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 */
public enum Rank {
    LEVEL0(0, 1, 0, "user"),
    LEVEL1(200, 1 - 0.02, 20, "level1"),
    LEVEL2(300, 1 - 0.03, 30, "level2"),
    LEVEL3(500, 1 - 0.05, 50, "level3"),
    ;

    private final int threshold;
    private final double ratio;
    private final int credit;
    private final String des;

    Rank(int threshold, double ratio, int credit, String des) {
        this.threshold = threshold;
        this.ratio = ratio - Default.RESERVE_RATIO;
        this.credit = credit;
        this.des = des;
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

    public String getDes() {
        return des;
    }
}
