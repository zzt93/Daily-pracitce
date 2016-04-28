package pearls;

/**
 * Created by zzt on 4/28/16.
 * <h3>Problem</h3>
 * <p>This models the problem of swapping nonadjacent blocks of memory: abc -> cba</p>
 * <h3>Solution</h3>
 * abc -> abc1c2; len(c2) == len(a) -> c2bc1a; ->
 * solve sub-problem `bc1 -> c1b` -> c2c1ba -> c1c2ba
 * <hr>
 * abc -> ab1b2c; rotate -> b2cab1 -> solve sub-problem `b2c`, `ab1`
 * <hr>
 * abc -> cba -> rotate `ba` to `ab` -> cab
 */
public class SwapNonAdjacent<T> {

    private T[] array;

    public SwapNonAdjacent(T[] array) {
        this.array = array;
    }
}
