package lambda;

import Auxiliary.Employee;
import enum_type.Department;
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
    private static final int CAPACITY = 3;
    private ArrayList<Employee> employees = new ArrayList<>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Collect() {
        final Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            employees.add(new Employee(
                    (i % 2 == 0) ? Gender.FEMALE : Gender.MALE,
                    Department.getRandomDepartment(),
                    random.nextDouble(),
                    new BigInteger(60, random).toString(32),
                    random.nextInt(100),
                    new BigInteger(40, random).toString(32)
            ));
        }
    }

    public Map<Gender, List<Employee>> byGender() {
        return employees
                .stream()
                .collect(
                        Collectors.groupingBy(Employee::getGender));
    }

    public Map<Gender, List<String>> genderName() {
        return employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                // produce name list
                                Collectors.mapping(
                                        Employee::getName,
                                        // The Collector parameter is called a downstream collector.
                                        // This is a collector that the Java runtime applies to
                                        // the results of another collector. -- collectors returned by groupBy
                                        Collectors.toList()
                                )
                        )
                        /*
                        Consequently, this groupingBy operation enables you to
                        apply a collect method to the List values created by the groupingBy operator.
                        ie, first groupBy gender than mapping it to name
                         */
                );
    }

    public Map<Gender, Integer> totalAge() {
        return employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.reducing(
                                        0, Employee::getAge, Integer::sum
                                )
                        )
                );
    }

    public Map<Gender, Double> averAge() {
        return employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.averagingInt(Employee::getAge)
                        )
                );
    }

    public static void main(String[] args) {
        final Collect collect = new Collect();
        System.out.println(collect.byGender());
        System.out.println(collect.genderName());
    }
}
