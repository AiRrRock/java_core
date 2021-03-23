package ru.geekbrains.java.level_1.lesson_5;

import com.sun.istack.internal.NotNull;

public class Employee {
    private static final String OUTPUT_TEMPLATE = "Name = %s +\nposition = %s\nemail = %s\nphone = %s\nsalary = %d\nage = %d\n";
    @NotNull
    private String name;
    @NotNull
    private String position;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    private int salary;
    private int age;

    public Employee(@NotNull String name, @NotNull String position, @NotNull String email,
                    @NotNull String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }


    public void printInfo() {
        System.out.println(this.toString());
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format(OUTPUT_TEMPLATE, name, position, email, phone, salary, age);
    }

}
