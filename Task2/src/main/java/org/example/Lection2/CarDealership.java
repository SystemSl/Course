package org.example.Lection2;

import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private List<Car> cars;
    public CarDealership() {
        this.cars = new ArrayList<>();
    }

    public boolean addCar(Car car) {
        if (this.cars.stream().noneMatch(existingCar -> existingCar.equals(car))) {
            this.cars.add(car);
            return true;
        } else {
            return false;
        }
    }

    public List<Car> findCarsByManufacturer(String manufacturer) {
        return this.cars.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double getAveragePriceForType(Car.CarType type) {
        OptionalDouble averagePrice = this.cars.stream()
                .filter(car -> car.getType() == type)
                .mapToDouble(Car::getPrice)
                .average();

        return averagePrice.isPresent() ? averagePrice.getAsDouble() : Double.NaN;
    }

    public List<Car> getSortedByYear() {
        return this.cars.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<Car.CarType, Long> getCountByType() {
        return this.cars.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    public Optional<Car> getOldestCar() {
        return this.cars.stream()
                .min(Comparator.comparingInt(Car::getYearOfProduction));
    }
    public Optional<Car> getNewestCar() {
        return this.cars.stream()
                .max(Comparator.comparingInt(Car::getYearOfProduction));
    }

    public List<Car> getCars() {
        return cars;
    }
}
