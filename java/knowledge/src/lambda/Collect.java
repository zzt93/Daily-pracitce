package lambda;

import enum_type.Gender;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 7/7/15.
 * <p>
 * Description: learn collect by code and example
 */
public class Collect {
    private static final int CAPACITY = 10;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Collect() {
        final Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            employees.add(new Employee(
                    (i % 2 == 0) ? Gender.FEMALE : Gender.MALE,
                    random.nextDouble(),
                    new BigInteger(130, random).toString(32),
                    random.nextInt(),
                    new BigInteger(40, random).toString(32)
            ));
        }
    }

    public Map<Gender, List<Employee>> byGender() {
        Map<Gender, List<Employee>> res =
                employees
                        .stream()
                        .collect(
                                Collectors.groupingBy(Employee::getGender));
        return res;
    }
}
