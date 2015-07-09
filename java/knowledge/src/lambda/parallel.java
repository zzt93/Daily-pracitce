package lambda;

import Auxiliary.Employee;
import enum_type.Gender;

import java.util.ArrayList;

/**
 * Created by zzt on 7/9/15.
 * <p>
 * Description:
 */
public class parallel {
    private ArrayList<Employee> employees;

    public parallel() {
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


}
