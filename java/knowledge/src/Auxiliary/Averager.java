package Auxiliary;

import java.util.function.DoubleConsumer;

/**
 * Created by zzt on 6/22/15.
 * <p>
 * Description:
 */
public class Averager implements DoubleConsumer {
    private double total = 0;
    private double count = 0;

    public double average() {
        return count > 0 ? ((double) total) / count : 0;
    }

    public void combine(Averager other) {
        total += other.total;
        count += other.count;
    }

    @Override
    public void accept(double value) {
        total += value;
        count++;
    }

}
