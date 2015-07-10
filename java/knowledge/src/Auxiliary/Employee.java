package Auxiliary;

import enum_type.Department;
import enum_type.Gender;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by zzt on 7/7/15.
 * <p>
 * Description: Auxiliary class for collect
 */
public class Employee {
    private static Random random = new Random(34);
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

    @Override
    public String toString() {
        return "Employee{" +
                "gender=" + gender +
                ", department=" + department +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", eNum='" + eNum + '\'' +
                '}';
    }

    public static String randomString(int size) {
        return new BigInteger(size * 5, random).toString(32);
    }

    public static int randomAge() {
        return 20 + random.nextInt(40);
    }
}
