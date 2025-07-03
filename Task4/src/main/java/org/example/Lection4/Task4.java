package org.example.Lection4;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Task4 {
    public static void main(String[] args) {

        Printable printer = () -> System.out.println("Print!");
        printer.print();

        Predicate<String> notNull = str -> str != null;
        System.out.println(notNull.test("Print!"));
        System.out.println(notNull.test(null));

        Predicate<String> notEmpty = str -> !str.isEmpty();
        System.out.println(notEmpty.test("Print!"));
        System.out.println(notEmpty.test(""));

        Predicate<String> notNullAndNotEmpty = notNull.and(notEmpty);
        System.out.println(notNullAndNotEmpty.test("Print!"));
        System.out.println(notNullAndNotEmpty.test(""));
        System.out.println(notNullAndNotEmpty.test(null));

        Predicate<String> checkString = str -> (str.startsWith("J") || str.startsWith("N")) && str.endsWith("A");
        System.out.println(checkString.test("JA"));
        System.out.println(checkString.test("NA"));
        System.out.println(checkString.test("NO"));

        Consumer<HeavyBox> loadingAction = box -> System.out.println("Отгружаем ящик с весом " + box.weight);
        Consumer<HeavyBox> shippingAction = box -> System.out.println("Отправляем ящик с весом " + box.weight);
        Consumer<HeavyBox> completeAction = loadingAction.andThen(shippingAction);
        HeavyBox heavyBox = new HeavyBox(100);
        completeAction.accept(heavyBox);

        Function<Integer, String> classifyNumber = num -> {
            if (num > 0) {
                return "Положительное число";
            } else if (num < 0) {
                return "Отрицательное число";
            } else {
                return "Ноль";
            }
        };
        System.out.println(classifyNumber.apply(-5));
        System.out.println(classifyNumber.apply(0));
        System.out.println(classifyNumber.apply(10));

        Supplier<Integer> randomNumberGenerator = () -> new Random().nextInt(11);
        System.out.println(randomNumberGenerator.get());
        System.out.println(randomNumberGenerator.get());
    }
}

class HeavyBox {
    public int weight;

    public HeavyBox(int weight) {
        this.weight = weight;
    }
}
