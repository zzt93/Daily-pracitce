package Auxiliary;

import enum_type.Department;
import enum_type.Gender;

/**
 * Created by zzt on 7/7/15.
 * <p>
 * Description: Auxiliary class for collect
 */
public class Employee {
    private Gender gender;
    private Department department;
    private double salary;
    private String name;
    private int age;
    private String eNum;

    public Department getDepartment() {
        return department;
    }

    public Gender getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String geteNum() {
        return eNum;
    }

    public Employee(Gender gender, Department department, double salary, String name, int age, String eNum) {
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.name = name;
        this.age = age;
        this.eNum = eNum;
    }

}
