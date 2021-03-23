package ru.geekbrains.java.level_1.lesson_5;


import javax.annotation.Nonnull;

public class Employee {
    private static final String OUTPUT_TEMPLATE = "Name = %s +\nposition = %s\nemail = %s\nphone = %s\nsalary = %d\nage = %d\n";
    @Nonnull
    private String name;
    @Nonnull
    private String position;
    @Nonnull
    private String email;
    @Nonnull
    private String phone;
    private int salary;
    private int age;

    public Employee(@Nonnull String name, @Nonnull String position, @Nonnull String email,
                    @Nonnull String phone, int salary, int age) {
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
