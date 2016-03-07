package mis;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by zzt on 3/7/16.
 * <p>
 * Usage:
 */
public class ChartDataWrapper implements Serializable {
    public static final long serialVersionUID = 42L;

    private final int[] labels;
    private final HashMap<Integer, double[]> res;

    public int[] getLabels() {
        return labels;
    }

    public HashMap<Integer, double[]> getRes() {
        return res;
    }

    public ChartDataWrapper(int[] labels, HashMap<Integer, double[]> res) {
        this.labels = labels;
        this.res = res;
    }
}
