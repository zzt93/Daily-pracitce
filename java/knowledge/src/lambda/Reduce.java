package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 6/22/15.
 * <p>
 * Description: learn reduction operation
 *
 */
public class Reduce {
    public static final int CAPACITY = 3;
    ArrayList<Integer> ints = new ArrayList<>(CAPACITY);

    public Reduce() {
//        ints.add(4);
        final Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            ints.add(random.nextInt(4));
        }
    }

    public double sum() {
        return ints.stream().mapToDouble(Integer::doubleValue).sum();
    }

    public double square_sum() {
        return ints.stream()
                .mapToDouble(Integer::doubleValue)
                .reduce(0.0, (s, b) -> s + b * b);
    }



    public double aver() {
        return ints.stream()
                .mapToDouble(Integer::doubleValue)
                .collect(Averager::new, Averager::accept, Averager::combine).average();
    }

    public List<String> description() {
        return ints.stream()
                .map(a -> "0" + a)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> getInts() {
        return ints;
    }

    public static void main(String[] args) {
        Reduce test = new Reduce();
        System.out.println(test.getInts());
        System.out.println(test.sum());
        System.out.println(test.square_sum());
        System.out.println(test.description());
        System.out.println(test.aver());
    }
}
