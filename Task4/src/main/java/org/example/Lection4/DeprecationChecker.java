package org.example.Lection4;

import java.lang.reflect.Method;

public class DeprecationChecker {
    public static void check(Class<?> clazz) {
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = clazz.getAnnotation(DeprecatedEx.class);
            System.out.printf("Внимание: класс '%s' устарел. Альтернатива: %s%n", clazz.getSimpleName(), annotation.message());
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                System.out.printf("Внимание: метод '%s.%s' устарел. Альтернатива: %s%n", clazz.getSimpleName(), method.getName(), annotation.message());
            }
        }
    }

    public static void main(String[] args) {
        check(MyOldClass.class);
    }
}

@DeprecatedEx(message = "Используйте NewClass вместо старого.")
class MyOldClass {

    @DeprecatedEx(message = "Используйте newMethod()")
    public void oldMethod() {
        System.out.println("Это старый метод.");
    }

    public void newMethod() {
        System.out.println("Это новый метод.");
    }
}

class NewClass {
    public void newMethod() {
        System.out.println("Это новая замена старому классу.");
    }
}
