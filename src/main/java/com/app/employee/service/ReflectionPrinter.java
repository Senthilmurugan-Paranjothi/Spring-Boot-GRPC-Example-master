package com.app.employee.service;

import java.lang.reflect.Field;

public class ReflectionPrinter {

    public static void printObjectFields(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return;
        }

        Class<?> clazz = obj.getClass();
        System.out.println("Class: " + clazz.getName());

        while (clazz != null) {  // Walk up inheritance chain
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    System.out.println(field.getName() + " = " + field.get(obj));
                } catch (IllegalAccessException e) {
                    System.out.println(field.getName() + " = <inaccessible>");
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}

