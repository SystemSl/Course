package org.example.Lection2;

import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {

        // part 1

        int[] carYears = new int[50];
        Random random = new Random();
        for (int i = 0; i < carYears.length; i++) {
            carYears[i] = random.nextInt((2025 - 2000 + 1)) + 2000;
        }
        int sum = 0;

        System.out.print("Year > 2015: ");
        for (int year : carYears) {
            System.out.print(year > 2015 ? year + " " : "");
            sum += year;
        }

        System.out.println();
        System.out.println("Average year: " + sum/carYears.length);
        System.out.println();

        // part 2

        ArrayList<String> cars = new ArrayList<>();
        cars.add("Toyota Camry");
        cars.add("BMW X5");
        cars.add("Honda Civic");
        cars.add("Tesla Model S");
        cars.add("Audi A4");
        cars.add("Toyota Camry");
        cars.add("Ford Mustang");
        cars.add("Volkswagen Golf");
        cars.add("BMW X5");
        cars.add("Hyundai Sonata");

        Set<String> uniqueCars = new HashSet<>(cars);
        List<String> sortedCars = new ArrayList<>(uniqueCars);
        sortedCars.sort(Comparator.reverseOrder());
        System.out.println(sortedCars);
        Set<String> resultSet = new LinkedHashSet<>(sortedCars);

        List<String> modifiedCars = new ArrayList<>();
        for (String model : resultSet) {
            if (model.contains("Tesla")) {
                modifiedCars.add("ELECTRO_CAR");
            } else {
                modifiedCars.add(model);
            }
        }
        resultSet = new LinkedHashSet<>(modifiedCars);
        System.out.println(resultSet);
        System.out.println();

        // part 3

        Car car1 = new Car("ABC123", "Camry", "Toyota", 2020, 50000, 250000, Car.CarType.SEDAN);
        Car car2 = new Car("DEF456", "X5", "BMW", 2021, 30000, 500000, Car.CarType.SUV);
        Car car3 = new Car("GHJ789", "Civic", "Honda", 2019, 60000, 200000, Car.CarType.HATCHBACK);
        Car car4 = new Car("ASD789", "Civic", "Honda", 2020, 30000, 270000, Car.CarType.HATCHBACK);
        Car duplicateCar = new Car("ABC123", "Another Model", "Different Manufacturer", 2022, 10000, 300000, Car.CarType.HYBRID);
        HashSet<Car> carSet = new HashSet<>();
        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);
        carSet.add(car4);
        carSet.add(duplicateCar);
        System.out.println(carSet);
        List<Car> listCars = new ArrayList<>(carSet);
        listCars.sort(null);
        System.out.println(listCars);
        System.out.println();

        // part 4

        List<Car> carList = new ArrayList<>(carSet);
        List<Car> filteredCars = carList.stream()
                .filter(car -> car.getMileage() < 50000)
                .toList();
        System.out.println(filteredCars);
        List<Car> sortedByPriceDescending = carList.stream()
                .sorted(Comparator.comparingInt(Car::getPrice).reversed())
                .toList();
        System.out.println(sortedByPriceDescending);
        List<Car> topThreeExpensive = carList.stream()
                .sorted(Comparator.comparingInt(Car::getPrice).reversed())
                .limit(Math.min(3, carList.size()))
                .toList();
        System.out.println(topThreeExpensive);
        OptionalDouble avgMileage = carList.stream().mapToInt(Car::getMileage).average();
        System.out.println(avgMileage.orElse(Double.NaN));
        Map<String, List<Car>> groupedByManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        groupedByManufacturer.forEach((manufacturer, list) -> {
            System.out.println(manufacturer + ":");
            list.forEach(System.out::println);
        });
    }
}
