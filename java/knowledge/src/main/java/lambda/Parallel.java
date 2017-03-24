package lambda;

import auxiliary.Employee;
import enum_type.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by zzt on 7/9/15.
 * <p>
 * Description:
 */
public class Parallel {
    private ArrayList<Employee> employees;

    public Parallel() {
        employees = new Collect().getEmployees();
    }

    public double genderAverage(Gender gender) {
        return employees
                .parallelStream()
                .filter(e -> e.getGender() == gender)
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble();
    }

    public ConcurrentMap<Gender, List<Employee>> byGender() {
        return employees
                .parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(
                                Employee::getGender
                        )
                );
    }

    public void describe() {
        System.out.println("\nDescribe");
        employees
                .parallelStream()
                .forEach(System.out::println);
    }

    public void describeOrderly() {
        System.out.println("\nDescribe orderly");
        employees
                .parallelStream()
                .forEachOrdered(System.out::println);
    }

    public static void main(String[] args) {
        final Parallel parallel = new Parallel();
        System.out.println(parallel.genderAverage(Gender.MALE));
        System.out.println(parallel.byGender());
        parallel.describe();
        parallel.describeOrderly();
    }
}
