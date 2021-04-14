package ru.geekbrains.java.level3.lesson7.test_runners;

import org.junit.jupiter.api.Test;
import ru.geekbrains.java.level3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java.level3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java.level3.lesson7.annotations.Priority;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public static boolean start(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Get all methods and store them
        Method beforeAll = null;
        Method afterAll = null;
        Map<Integer, List<Method>> tests = new HashMap<>();
        for (Method method : clazz.getDeclaredMethods()) {
            AfterSuite afterSuite = method.getAnnotation(AfterSuite.class);
            BeforeSuite beforeSuite = method.getAnnotation(BeforeSuite.class);
            Test test = method.getAnnotation(Test.class);
            Priority priority = method.getAnnotation(Priority.class);
            if (afterSuite != null) {
                if (afterAll != null) {
                    throw new RuntimeException("There can be only 1 @AfterSuite method");
                } else {
                    afterAll = method;
                }
            }
            if (beforeSuite != null) {
                if (beforeAll != null) {
                    throw new RuntimeException("There can be only 1 @BeforeSuite method");
                } else {
                    beforeAll = method;
                }
            }
            if (test != null) {
                int intPriority = 5;
                if (priority != null) {
                    intPriority = priority.priority();
                }
                intPriority = intPriority < 0 ? 0 : intPriority;
                intPriority = intPriority > 10 ? 10 : intPriority;
                List<Method> methods = tests.get(intPriority);
                if (methods == null) {
                    methods = new ArrayList<>();
                }
                methods.add(method);
                tests.put(intPriority, methods);
            }

        }
        Object obj = clazz.getDeclaredConstructor().newInstance();
        if (beforeAll != null) {
            beforeAll.setAccessible(true);
            beforeAll.invoke(obj);
        }

        for (int i = 10; i > 0; i--) {
            List<Method> methods = tests.get(i);
            if (methods != null) {
                for (Method method : methods) {
                    method.setAccessible(true);
                    method.invoke(obj);
                }
            }
        }

        if (afterAll != null) {
            afterAll.setAccessible(true);
            afterAll.invoke(obj);
        }

        return true;
    }


    public static boolean start(String clazz) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class cl = Class.forName(clazz);
        return start(cl);
    }

}
