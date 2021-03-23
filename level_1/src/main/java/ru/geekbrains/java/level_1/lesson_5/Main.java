package ru.geekbrains.java.level_1.lesson_5;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Smb1", "manager", "aaa@google.com", "+78889991112", 10000000, 95);
        employees[1] = new Employee("Smb2", "manager", "aaa@google.com", "+78889991112", 10000000, 44);
        employees[2] = new Employee("Smb3", "manager", "aaa@google.com", "+78889991112", 10000000, 43);
        employees[3] = new Employee("Smb4", "manager", "aaa@google.com", "+78889991112", 10000000, 12);
        employees[4] = new Employee("Smb5", "manager", "aaa@google.com", "+78889991112", 10000000, 13);

        for (Employee e : employees) {
            if (e.getAge() > 40) {
                e.printInfo();
            }
        }

    }
}
